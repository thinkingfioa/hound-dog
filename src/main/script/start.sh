#!/bin/sh
cd `dirname $0`
echo `basename $0` is at `pwd`

export ESCLIENT_HOME=..
CONFIG_DIR=$ESCLIENT_HOME/config

RESOLVED_CONFIG_DIR=`cd "$CONFIG_DIR"; pwd`
export CLASSPATH=$RESOLVED_CONFIG_DIR

for i in `ls $ESCLIENT_HOME/lib/*.jar`; do
	CLASSPATH=$i:$CLASSPATH
done
nohup java -classpath $CLASSPATH -Xms512m -Xmx4096m -XX:PermSize=256m -XX:MaxPermSize=1024m -XX:+HeapDumpOnOutOfMemoryError org.vlis.dog.start.StartHoundDog $* &
echo "start success"
