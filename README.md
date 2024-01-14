# project9_OC

At project root :

### Build the Docker Images
Run :
```
.\buildAll.bat
```

### Run the microservices and databases Docker Containers

Make sure ports 3306, 27017 and 9004 are free then run :
```
docker-compose up
```

### Using the app

You need to have both the front and back containers running to use the app.
Go to localhost:80 in your browser anc click login at the top right to log or register to the app.

You can register as a doctor and can see all the patients, add patients, add notes to patients.