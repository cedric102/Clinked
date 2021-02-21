## Instruction:
- Implement a RestAPI application in which a user could list articles present in a database and add an article reference in a database.
- Only the Admin could monitor the statistics of how many articles have been added for each day during a week ( In this case the past week from the current date )
- The Interaction is entirely Browser Based.

## Implementation:
- Makes use of Spring and Thymeleaf
- The admin does not just monitor the statistics of the week. He also can see the pageinated articles list in the same page.
- Note: A Nav Bar allows the user to switch view

## Main Endpoints:
- The Base Endpoint for Admin is : /admin/stat
- The Base Endpoint for Client is : /articles
- View the Stat Vew: /admin/stat/view
- View the Stat as JSON: /admin/stat
- View the Paginated Article List: /articles

## Structure:
There are two controllers: Admin ( AdminStatisticController ) and User ( ClientController ). Both inherit from a Base Class ( DatabaseManagement.java ), which contain the methods that Admin and User have in common. 

## Discussion about Alternative Implementation :
It had been considered for Admin to inherit from Client.
- The Admin Class uses the same methods as for the normal User Class. Consequently, one could assume that Admin could inherit from User to keep the code DRY. However, it has been decided to divert from this approach because:
 - Not sure what the endpoint would turn out to be if Admin inherits from Client.
 - The endpoints need to be different to put in place the Admin permission management for the statistics.
 - Performing this inheritance may cause an issue of one view linking to an incorrect endpoint. See below:

- One View should normally be sufficient given that the onely difference between Admin and Client is the Statistics Requirement:
  - If the Client calls the View, It could not send the Statistics data to the View resulting in the desired view and the absence of statistics
  - If the Admin calls the View, The statistics data gets sent to the View resulting in the desired view with the statistics.
However, the view would has the "Add Article To Database" form that links to either Admin or Client endpoint once the form is submitted. If only one View is being used, it would always point to either of the endpoint.

Therefore, it has been decided to create two controllers respectively for the User and the Admin and each will return their respective View and each view would link to their respective endpoint.
