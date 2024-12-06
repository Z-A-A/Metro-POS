-- Create Super Admin Table
CREATE TABLE SuperAdmin (
                            SuperAdminID INT AUTO_INCREMENT PRIMARY KEY,
                            Name VARCHAR(100) NOT NULL,
                            Email VARCHAR(100) UNIQUE NOT NULL,
                            Password VARCHAR(255) NOT NULL
);

-- Create Branches Table
CREATE TABLE Branches (
                          BranchID INT AUTO_INCREMENT PRIMARY KEY,
                          BranchCode VARCHAR(20) UNIQUE NOT NULL,
                          BranchName VARCHAR(100) NOT NULL,
                          City VARCHAR(50) NOT NULL,
                          Address VARCHAR(200) NOT NULL,
                          Phone VARCHAR(20) NOT NULL,
                          NumEmployees INTEGER DEFAULT 0,
                          IsActive BOOLEAN DEFAULT TRUE
);

-- Create Branch Manager Table
CREATE TABLE BranchManagers (
                                ManagerID INT AUTO_INCREMENT PRIMARY KEY,
                                Name VARCHAR(100) NOT NULL,
                                EmployeeNo VARCHAR(20) UNIQUE NOT NULL,
                                Email VARCHAR(100) UNIQUE NOT NULL,
                                Password VARCHAR(255) NOT NULL,
                                BranchCode VARCHAR(20) NOT NULL,
                                Salary DECIMAL(10, 2),
                                ContactPhone VARCHAR(20),
                                IsActive BOOLEAN DEFAULT TRUE,
                                FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

-- Create Users Table
CREATE TABLE Cashier (
                         EmployeeNo INT AUTO_INCREMENT PRIMARY KEY,
                         Name VARCHAR(100) NOT NULL,
                         Email VARCHAR(100) UNIQUE NOT NULL,
                         Password VARCHAR(255) NOT NULL,
                         BranchCode VARCHAR(20),
                         Salary DECIMAL(10, 2),
                         IsActive BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

CREATE TABLE DataEntryOperator (
                                   EmployeeNo INT AUTO_INCREMENT PRIMARY KEY,
                                   Name VARCHAR(100) NOT NULL,
                                   Email VARCHAR(100) UNIQUE NOT NULL,
                                   Password VARCHAR(255) NOT NULL,
                                   BranchCode VARCHAR(20),
                                   Salary DECIMAL(10, 2),
                                   IsActive BOOLEAN DEFAULT TRUE,
                                   FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

-- Create Vendors Table
CREATE TABLE Vendors (
                         VendorID INT AUTO_INCREMENT PRIMARY KEY,
                         VendorName VARCHAR(100) NOT NULL,
                         VendorAddress VARCHAR(200),
                         VendorPhone VARCHAR(20),
                         IsActive BOOLEAN DEFAULT TRUE
);

-- Create Products Table
CREATE TABLE Products (
                          ProductID INT AUTO_INCREMENT PRIMARY KEY,
                          ProductName VARCHAR(100) NOT NULL,
                          Category VARCHAR(50) NOT NULL,
                          OriginalPrice DECIMAL(10, 2) NOT NULL,
                          SalePrice DECIMAL(10, 2) NOT NULL,
                          PricePerUnit DECIMAL(10, 2) NOT NULL,
                          PricePerCarton DECIMAL(10, 2) NOT NULL,
                          VendorID INTEGER,
                          CurrentStock INTEGER DEFAULT 0,
                          ProductImagePath VARCHAR(255),
                          IsActive BOOLEAN DEFAULT TRUE,
                          FOREIGN KEY (VendorID) REFERENCES Vendors(VendorID)
);

-- Create Sales Table
CREATE TABLE Sales (
                       SaleID INT AUTO_INCREMENT PRIMARY KEY,
                       SaleDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        BillNumber VARCHAR(50) ,
                       BranchCode VARCHAR(20),
                       ProductID INTEGER,
                       Quantity INTEGER NOT NULL,
                       UnitPrice DECIMAL(10, 2) NOT NULL,
                       TotalAmount DECIMAL(10, 2) NOT NULL,
                       CashierUserID INTEGER,
                        FOREIGN KEY (BillNumber) REFERENCES Bills(BillNumber),
                       FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode),
                       FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
                       FOREIGN KEY (CashierUserID) REFERENCES Cashier(EmployeeNo)
);

-- Create Reports Table
CREATE TABLE Reports (
                         ReportID INT AUTO_INCREMENT PRIMARY KEY,
                         BranchCode VARCHAR(20),
                         ReportType VARCHAR(20) CHECK (ReportType IN ('Sales', 'RemainingStock', 'Profit')),
                         ReportDate DATE DEFAULT CURRENT_DATE,
                         TotalSales DECIMAL(10, 2),
                         TotalProfit DECIMAL(10, 2),
                         RemainingStock INTEGER,
                         GeneratedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);

CREATE TABLE Bills (
                       BillID INT AUTO_INCREMENT PRIMARY KEY,
                       BillNumber VARCHAR(50) UNIQUE NOT NULL,
                       BranchCode VARCHAR(20) NOT NULL,
                       CashierName VARCHAR(100) NOT NULL,
                       Date DATE NOT NULL,
                       TotalAmount DECIMAL(10, 2) NOT NULL,
                       FOREIGN KEY (BranchCode) REFERENCES Branches(BranchCode)
);