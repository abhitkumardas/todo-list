mvn clean install
app_port=$(ps -ef | grep 'todo-list-0.0.1-SNAPSHOT.jar' | grep -v 'grep' | awk '{ printf $2 }')
sudo kill $app_port
echo Killed TODO_LIST_Application and Starting New
echo --------------------------------------------------------------------------------------------------------------
nohup java -jar "target/todo-list-0.0.1-SNAPSHOT.jar" &
