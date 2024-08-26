# <span style="color: #4CAF50;">eCommerce API Documentation</span>
This API provides endpoints to manage products, orders and order details for an eCommerce platform.

## <span style="color: #2196F3;">Available Routes</span>
### <span style="color: #FF5722;">Product</span>
- **GET** `/api-app/producto`: Lists all available products.
- **GET** `/api-app/producto/{productName}`: Retrieves a specific product by its Name.
- **POST** `/api-app/producto`: Adds a new product.
- **PUT** `/api-app/producto`: Updates an existing product.
- **DELETE** `/api-app/producto/{id}`: Deletes a specific product by its ID.

### <span style="color: #FF5722;">Order</span>
- **GET** `/api-app/orden`: Lists all available orders.
- **GET** `/api-app/orden/{id}`: Retrieves a specific order by its ID.
- **POST** `/api-app/orden`: Creates a new order with the provided details.
- **DELETE** `/api-app/orden/{id}`: Deletes a specific order by its ID.

### <span style="color: #FF5722;">Order Detail</span>
- **GET** `/api-app/detalle/{ordenId}`: Retrieves the details of a specific order by its ID.

## <span style="color: #2196F3;">Swagger UI Documentation</span>
You can access the interactive documentation of the API via Swagger UI at the following link:

[http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)
