# ShoppingApp
Shopping App which follows a clean MVVM architecture with Room, Dao, Retrofit

The app is designed using the Android MVVM architecture using the Jetpack APIs with Room database, Retrofit and other libraries.

Design
-----------------------
1. The App uses single Activity which hosts multiple fragments, ProducFragment to display the categories in Horizontal Recyclerview
and products in the form of card stacks. Another Fragment, CarFragment is for the UI for AddToCart Screen. This screen is also made visible when the device is shaken.

2. There are 3 RecyclerViews used, one for horizontal scrolling of categories, for card stack display of products and another for each item for addToCart screen.
Each of these screens further use their respective Adapters and ViewHolders.

3. Each Fragment component has a ViewModel which provides the data to these fragment classes.
The ViewModel fetches the data from repository which can be either the server fetched repository or the assigned data entity classes for these.

4. Since it is a MVVM architecture, there are few data classes which are also used for the entity creation.
   Product, Category, CartProductModel(for in cart items)

5. For the retrofit webservice, we have used an interface for its creation and for using its methods for fetching all categories and products from the end point provided.

6. In the entity, we needed to store List<Category> since it is not directly possible with Room, the project uses a DataConverter to convert the List to String.

7. For sensing the shake of the device, sensor manager with sensor listeners have been used

Data Entity Model
---------------------
Category, Product, CartProductModel

Enhancements
-------------------------
1. Due to the scope of the project, the dependency injection using Hilt has only been added for the repository, DAO and database layer.
This can be enhanced for the complete workflow to avoid any manual dependency injection. Currently the di package has been excluded from the project.

2. Currently, project uses external library for card swipe feature, but the same can be achieved using ViewPager.


External Libraries Used
------------------------

* [Architecture]
    * [Room] - Used for creating a database for offline storage of products list
    * [ViewModel] - Used as a layer to provde data to the UI components from the data repository
    * [Retrofit] - Used for server communication; thread safe
    * [UI] - Single Activity Multi Fragment structure; RecyclerView for list structure
    * [Coroutines] - For communication with the DAO, for thread safety
    * [View Bindings] - In lieu of findViewById, View bindings have been used for better null safety and performance
    * [Data Bindings] - This is used to bind observable data to UI elements
* [Third Party Libraries]
    * To display the products in the form of swipable cards, the project uses "com.yuyakaido.android:card-stack-view:2.3.4"
    * To loading images in the views, Glide is used
    * Hilt[In Progress] - For dependency injection. For this project scope only used for dao, db and repository workflow


