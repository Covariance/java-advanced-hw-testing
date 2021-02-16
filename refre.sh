#!/bin/bash

pushd ../java-advanced || exit

git fetch origin
git fetch source
git pull origin master
git pull --rebase source master

popd || exit