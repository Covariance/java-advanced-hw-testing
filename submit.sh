#!/bin/bash

rsync -av --exclude='*Test.java' --exclude='*Tester.java' \
      --exclude='*.iml' --exclude='*module-info.java' \
      ./modules/info.kgeorgiy.ja.фамилия.walk/ \
      ../java-advanced/java-solutions
      
cd ../java-advanced || exit

git push origin master
