-- Initialize sample data for GraphQL application
-- Categories
INSERT INTO categories (id, name, images) VALUES
  (1, 'Electronics', 'elec.png'),
  (2, 'Books', 'books.png'),
  (3, 'Fashion', 'fashion.png')
ON CONFLICT (id) DO NOTHING;

-- Users
INSERT INTO users (id, fullname, email, password, phone) VALUES
  (1, 'Nguyen Van A', 'a@example.com', 'passA', '0900000001'),
  (2, 'Tran Thi B', 'b@example.com', 'passB', '0900000002')
ON CONFLICT (id) DO NOTHING;

-- Many-to-many user_categories
INSERT INTO user_categories (user_id, category_id) VALUES
  (1, 1), (1, 2), (2, 2), (2, 3)
ON CONFLICT DO NOTHING;

-- Products
INSERT INTO products (id, title, quantity, description, price, user_id, category_id) VALUES
  (1, 'iPhone 15', 10, 'New phone', 23000000.00, 1, 1),
  (2, 'MacBook Air', 5, 'M2 Chip', 28000000.00, 1, 1),
  (3, 'Spring in Action', 20, 'Java Spring book', 650000.00, 2, 2),
  (4, 'T-Shirt', 50, 'Cotton', 150000.00, 2, 3)
ON CONFLICT (id) DO NOTHING;
