#!/bin/bash

rsync -av --exclude='*Test.java' --exclude='*Tester.java' \
      --exclude='*.iml' --exclude='*module-info.java' \
      ./modules/info.kgeorgiy.ja.фамилия.walk/ \
      ../java-advanced/java-solutions

cd ../java-advanced || exit

git add ./*
git commit -m "Automated commit"
git push origin master

cd ../java-advanced-2021 || exit
