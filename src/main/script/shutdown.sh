#! /bin/sh
cd `dirname $0`
echo `basename $0` is at `pwd`

hound_dog_path=`cd ..; pwd`
echo "Using hound_dog_path: hound_dog_path"
pid=`ps -ef|grep java|grep hound_dog_path |awk '{print $2}'`
echo "pid=[$pid]"
while [  -n "$pid" ]; do
    echo "kill $pid"
    kill -9 $pid
    sleep 1s
    pid=`ps -ef|grep java|grep hound_dog_path |awk '{print $2}'`
done
echo "shutdown ok"
