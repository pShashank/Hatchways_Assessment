# Hatchways Back-end Assessment

## Data Source
You will be building an API that requires you to fetch data from this API:
* _Request_:
    
    _Route_: [https://api.hatchways.io/assessment/blog/posts]
    _Method_: GET<br>
    _Query Parameters_:
    
    | Field | Type             | Description                           |   
    |-------|------------------|---------------------------------------|
    | Tag   | String(Required) | The tag associated with the blog post.|  
    |       |                  |                                       | 

Notice that the parameter is a query parameter - you can read more about query
parameters here. An example of sending the tag parameter is
https://api.hatchways.io/assessment/blog/posts?tag=tech.
Our API can only filter one tag at a time - notice that the field “tag” is singular and not
plural.
It will return a JSON object with an array of blog posts. An example response is:
```
{
    "posts": [
    {
        "id": 1,
        "author": "Rylee Paul",
        "authorId": 9,
        "likes": 960,
        "popularity": 0.13,
        "reads": 50361,
        "tags": [ "tech", "health" ]
    }, ...
   ```
## API Requirements
You need the following routes in your API:

Route 1:

* _Request_:
    
     _Route_: [/api/ping]<br>
    _Method_: GET<br>
    _Query Parameters_:<br>
    Response body (JSON):
    ```
        {
            "success": true
        }
    ```
        Response status code: 200
        
Route 2:
* _Request_:
    
     _Route_: [/api/posts]<br>
    _Method_: GET<br>
    _Query Parameters_:<br>
    
      | Field     	| Type             	| Description                                                                                                    	| Default 	| Example      	|
      |-----------	|------------------	|----------------------------------------------------------------------------------------------------------------	|---------	|--------------	|
      | tags      	| String(Required) 	| The tag associated with the blog post.                                                                         	| N/A     	| science,tech 	|
      | sortBy    	| String(optional) 	| The field to sort the posts by. The acceptable fields are:<br>● id<br>● reads<br>● likes<br>● popularity 	| id      	| popularity   	|
      | direction 	| String(optional) 	| The direction for sorting. The acceptable fields are:<br>● desc<br>● asc                                 	| asc     	| asc          	|
      
* Successful Response:
    The API response will be a list of all the blog posts that have **at least one tag
    specified** in the tags parameter.
    The sortBy parameter specifies which field should be used to sort the returned
    results. This is an optional parameter, with a default value of `id`.
    The direction parameter specifies if the results should be returned in ascending
    order (if the value is "asc") or descending order (if the value is "desc"). The default
    value of the direction parameter is `asc`.
    
    Here is how the response should look:
    
    Here is how the response should look:
    Response body (JSON):
    ```
    {
        "posts": [
        {
            "id": 1,
            "author": "Rylee Paul",
            "authorId": 9,
            "likes": 960,
            "popularity": 0.13,
            "reads": 50361,
            "tags": [ "tech", "health" ]
        },
        ...
        ]
    }
    ```
    Response status code: 200

* Error Responses:
    If `tags` parameter is not present:
    Response body (JSON):
    ```
    {
        "error": "Tags parameter is required"
    }
    ```
    Response status code: 400
    If a `sortBy` or `direction` are invalid values, specify an error like below:
    Response body (JSON):
    ```
    {
        "error": "sortBy parameter is invalid"
    }
    ```
    Response status code: 400
    
Here is what you will need to do to complete this task:
<ul>
<li> For every tag specified in the tags parameter, fetch the posts with that tag using
    the Hatchways API (make a separate API request for every tag specified)</li>
<li> Combine all the results from the API requests above and remove all the repeated
    posts (try to be efficient when doing this)</li>
<li> You will get a better score on our assessment if you can make concurrent
requests to the API (making the requests in parallel) (we understand that this job
    is easier in some languages vs. others)</li>
    
We have provided an API with the correct solution. This should only be used to verify
your results. Do not call this API in your application. Here it is in action:
[https://api.hatchways.io/assessment/solution/posts?tags=history,tech&sortBy=likes&d
irection=desc]

## Step 3
An important part of development is testing. In this step, we want to see tests written
for your routes. **Do not use the solutions API route to perform testing in this step**. Think
about the different ways to test the app, and the best way to get good coverage.

## Step 4 (Bonus!)
Making API calls to other servers can be expensive. How can you reduce the number of
calls you make to a server? You can cache the results of an API call on your server. Try
to implement a server side cache to our API. Two tips are <ol><li> keep it simple </li> <li> feel
    free to use existing libraries/frameworks. </li>
