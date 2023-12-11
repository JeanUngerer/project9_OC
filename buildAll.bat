call mvn -f config-server/pom.xml compile jib:dockerBuild
call mvn -f dangerService/pom.xml compile jib:dockerBuild
call mvn -f eureka-server/pom.xml compile jib:dockerBuild
call mvn -f mApiGateway/pom.xml compile jib:dockerBuild
call mvn -f notesService/pom.xml compile jib:dockerBuild
call mvn -f patientsService/pom.xml compile jib:dockerBuild