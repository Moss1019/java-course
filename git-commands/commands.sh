
# clone a repo
git clone <https address>

# pull changes
git pull

# undo all changes before pulling
git checkout .

# stash changes before pulling
git stash

# add local stashed changes to newly pulled code
git stash pop

# check which files were changed
git status

# add changes to remote repo
git add .
git commit -m "commit message"
git push

# create a new local branch
git checkout -b <branch name>
