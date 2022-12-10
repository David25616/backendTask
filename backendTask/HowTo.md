## Finish the micro-service which features endpoints for receiving, processing and returning Thryve’s DynamicEpochValues provided as part of this task. Data is in /resource (backend_data.json)


### Things which need to be done:
### You should not take longer than 1.5 hours, even if you are not done which is fine

### Preparation
```
1. git init
2. git add .
3. git commit -m"init"
4. After 1.5 Hourse please create a new/final commit
5. Make sure to include .git file when sending the Solution
```



### GOALS
You can add/adjust/remove anything to/in/from the Project. 
Whatever you think is necessary to finish the task
```
1. Some bugs are in the service which needs to be fixed
2. Implement the endpoints in DataController (Use AuthTokenValidator or improve it and use it then)
3. Implement calculateAverageHeartRateForUser(int userId)
4. Validate if eventStore is working as expected
5. Make sure that the GlobalExceptionHandler works as it suppose to
6. Implement the test DataControllerTest.save_shouldNotSaveData_whenTokenIsMissing
7. Tests should succeed
```