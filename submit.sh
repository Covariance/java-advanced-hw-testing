#!/bin/bash

(( $# != 1 )) \
      && echo "Usage: $0 <hw>" \
      && exit

rsync -av --exclude='*Test.java' --exclude='*Tester.java' \
      --exclude='*.iml' --exclude='*module-info.java' \
      ./modules/info.kgeorgiy.ja.фамилия."$1"/ \
      ../java-advanced/java-solutions

pushd ../java-advanced || exit

git add ./java-solutions/info/kgeorgiy/ja/фамилия/"$1"/*
git commit -m "Add $1 [Automated commit]"
git push origin master

popd || exit