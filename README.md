# GraphQL Spring Boot Application

Ứng dụng GraphQL được xây dựng với Spring Boot và PostgreSQL.

## Cấu trúc dự án

- **Models**: User, Category, Product
- **GraphQL Schema**: Định nghĩa trong `src/main/resources/graphql/schema.graphqls`
- **Resolvers**: QueryResolver và MutationResolver
- **Database**: PostgreSQL với JPA/Hibernate

## Cách chạy ứng dụng

### Phương pháp 1: Sử dụng script khởi động
```bash
./start-app.sh
```

### Phương pháp 2: Chạy thủ công
```bash
# 1. Khởi động PostgreSQL
docker-compose up -d

# 2. Chạy ứng dụng Spring Boot
mvn spring-boot:run
```

## Truy cập ứng dụng

- **GraphQL Endpoint**: http://localhost:8080/graphql
- **GraphiQL Interface**: http://localhost:8080/graphiql

## Các query GraphQL mẫu

### Lấy danh sách categories
```graphql
query {
  categories {
    id
    name
    images
  }
}
```

### Lấy danh sách users
```graphql
query {
  users {
    id
    fullname
    email
  }
}
```

### Lấy danh sách products
```graphql
query {
  products {
    id
    title
    price
    quantity
    description
  }
}
```

### Lấy products theo category
```graphql
query {
  productsByCategory(categoryId: 1) {
    id
    title
    price
  }
}
```

## Dữ liệu mẫu

Ứng dụng tự động tạo dữ liệu mẫu khi khởi động:
- 3 categories: Electronics, Books, Fashion
- 2 users: Nguyen Van A, Tran Thi B
- 4 products: iPhone 15, MacBook Air, Spring in Action, T-Shirt

## Cấu hình database

- **Host**: localhost:5432
- **Database**: graphgl_db
- **Username**: graphgl_user
- **Password**: graphgl_pass

## Troubleshooting

Nếu gặp lỗi "relation does not exist", hãy đảm bảo:
1. PostgreSQL đang chạy: `docker-compose ps`
2. Database được khởi tạo đúng cách
3. JPA đã tạo các bảng cần thiết
