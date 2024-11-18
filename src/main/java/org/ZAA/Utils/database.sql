-- noinspection SqlNoDataSourceInspectionForFile

-- Metro POS System Database Schema
-- PostgreSQL Database

-- Drop existing tables if they exist
DROP TABLE IF EXISTS Reports CASCADE;
DROP TABLE IF EXISTS Sales CASCADE;
DROP TABLE IF EXISTS Products CASCADE;
DROP TABLE IF EXISTS Vendors CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Branches CASCADE;

-- Create Super Admin Table
CREATE TABLE SuperAdmin (
      SuperAdminID SERIAL PRIMARY KEY,
      Name VARCHAR(100) NOT NULL,
      Email VARCHAR(100) UNIQUE NOT NULL,
      Password VARCHAR(255) NOT NULL,
      CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Branches Table
CREATE TABLE Branches (
      BranchID SERIAL PRIMARY KEY,
      BranchCode VARCHAR(20) UNIQUE NOT NULL,
      BranchName VARCHAR(100) NOT NULL,
      City VARCHAR(50) NOT NULL,
      Address VARCHAR(200) NOT NULL,
      Phone VARCHAR(20) NOT NULL,
      NumEmployees INTEGER DEFAULT 0,
      IsActive BOOLEAN DEFAULT TRUE,
      CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Users Table
CREATE TABLE Users (
       UserID SERIAL PRIMARY KEY,
       Name VARCHAR(100) NOT NULL,
       EmployeeNo VARCHAR(20) UNIQUE NOT NULL,
       Email VARCHAR(100) UNIQUE NOT NULL,
       Password VARCHAR(255) NOT NULL,
       Role VARCHAR(30) NOT NULL CHECK (Role IN ('BranchManager', 'Cashier', 'DataEntryOperator')),
       BranchCode VARCHAR(20),
       Salary DECIMAL(10, 2),
       FirstLoginDate TIMESTAMP,
       LastLoginDate TIMESTAMP,
       IsActive BOOLEAN DEFAULT TRUE,
       FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

-- Create Vendors Table
CREATE TABLE Vendors (
     VendorID SERIAL PRIMARY KEY,
     VendorName VARCHAR(100) NOT NULL,
     VendorAddress VARCHAR(200),
     VendorPhone VARCHAR(20),
     IsActive BOOLEAN DEFAULT TRUE,
     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Products Table
CREATE TABLE Products (
      ProductID SERIAL PRIMARY KEY,
      ProductName VARCHAR(100) NOT NULL,
      Category VARCHAR(50) NOT NULL,
      OriginalPrice DECIMAL(10, 2) NOT NULL,
      SalePrice DECIMAL(10, 2) NOT NULL,
      PricePerUnit DECIMAL(10, 2) NOT NULL,
      PricePerCarton DECIMAL(10, 2) NOT NULL,
      VendorID INTEGER,
      CurrentStock INTEGER DEFAULT 0,
      IsActive BOOLEAN DEFAULT TRUE,
      CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (VendorID) REFERENCES Vendors(VendorID)
);

-- Create Sales Table
CREATE TABLE Sales (
   SaleID SERIAL PRIMARY KEY,
   SaleDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   BranchCode VARCHAR(20),
   ProductID INTEGER,
   Quantity INTEGER NOT NULL,
   UnitPrice DECIMAL(10, 2) NOT NULL,
   TotalAmount DECIMAL(10, 2) NOT NULL,
   CashierUserID INTEGER,
   FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode),
   FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
   FOREIGN KEY (CashierUserID) REFERENCES Users(UserID)
);

-- Create Reports Table
CREATE TABLE Reports (
     ReportID SERIAL PRIMARY KEY,
     BranchCode VARCHAR(20),
     ReportType VARCHAR(20) CHECK (ReportType IN ('Sales', 'RemainingStock', 'Profit')),
     ReportDate DATE DEFAULT CURRENT_DATE,
     TotalSales DECIMAL(10, 2),
     TotalProfit DECIMAL(10, 2),
     RemainingStock INTEGER,
     GeneratedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

-- Create Indexes for Performance
CREATE INDEX idx_users_email ON Users(Email);
CREATE INDEX idx_users_branch_code ON Users(BranchCode);
CREATE INDEX idx_products_vendor ON Products(VendorID);
CREATE INDEX idx_sales_product ON Sales(ProductID);
CREATE INDEX idx_sales_branch ON Sales(BranchCode);
CREATE INDEX idx_reports_branch ON Reports(BranchCode);

-- Comments for Table Descriptions
COMMENT ON TABLE Branches IS 'Stores information about different branch locations';
COMMENT ON TABLE Users IS 'Manages user accounts with different roles';
COMMENT ON TABLE Vendors IS 'Maintains vendor information';
COMMENT ON TABLE Products IS 'Tracks product details and inventory';
COMMENT ON TABLE Sales IS 'Records all sales transactions';
COMMENT ON TABLE Reports IS 'Generates and stores various types of reports';