#!/bin/sh
cd script
./compile.sh

cd ..
cd  projet

echo ""
echo "création de votre image en format png"
java -cp "build" main.Render

