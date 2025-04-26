package com.mossonthetree;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mossonthetree.artemis.QueProducer;
import com.mossonthetree.com.mossonthetree.model.Tick;
import com.mossonthetree.stream.MemoryOutputStream;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Program {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new DateTimeSerializer())
            .create();

    private static final Thread INPUT_WORKER;

    private static boolean isRunning = true;

    public static void main(String[] args) {
        var artemisUrl = "tcp://localhost:61616";
        var producer = new QueProducer(artemisUrl, "test-ticks");
        producer.start();
        var rand = new Random();
        var index = 0;
        var testTicks = readTicks();

        if(testTicks == null) {
            isRunning = false;
        }
        var stream = new MemoryOutputStream();
        while(isRunning) {
            try {
                Thread.sleep(333 + rand.nextInt(100, 3000));
            } catch (Exception ignored) {}
            var nextTick = testTicks.get(index);
            if(++index == testTicks.size()) {
                index = 0;
            }
            stream.clear();
            stream.write(GSON.toJson(nextTick));
            producer.publish(stream);
        }

        producer.close();
        try {
            INPUT_WORKER.join();
        } catch (Exception ignored) {}
    }

    private static List<Tick> readTicks() {
        try (var stream = new FileInputStream("mock-ticks.json")) {
            var jsonContent = new String(stream.readAllBytes());
            var typeToken = new TypeToken<List<Tick>>() {};
            return GSON.fromJson(jsonContent, typeToken);
        } catch (Exception ex) {
            return null;
        }
    }

    static class DateTimeSerializer implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.toString());
        }
    }

    static class DateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString());
        }
    }

    static {
        INPUT_WORKER = new Thread(() -> {
            Supplier<String> inputSupplier = () -> {
                var buffer = new byte[1024];
                try {
                    var read = System.in.read(buffer);
                    return new String(buffer, 0, read - 1);
                } catch (Exception ignored) {
                    return "";
                }
            };

            while(isRunning) {
                var input = inputSupplier.get();
                switch (input) {
                    case "-q" -> {
                        isRunning = false;
                    }
                }
            }
        });
        INPUT_WORKER.start();
    }
}
