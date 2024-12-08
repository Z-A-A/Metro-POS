import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Box,
  Grid2,
  TextField,
  Card,
  CardContent,
  Typography,
  Button,
  List,
  ListItem,
  ListItemText,
  Divider,
  CardMedia,
  Paper,
} from "@mui/material";
import SplashScreen from "./SplashScreen"
import ReceiptModal from "../components/ReceiptModal"
import ChangePasswordModal from "../components/ChangePasswordModal";

const CashierPage = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [cart, setCart] = useState([]);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [receiptOpen, setReceiptOpen] = useState(false);
  const [receiptData, setReceiptData] = useState(null);


  useEffect(() => {
    // Set a timeout to control the duration of the splash screen
    const timer = setTimeout(() => {
      fetchProducts();
    }, 3000); // Adjust the duration as needed

    return () => clearTimeout(timer);
  }, []);

  const fetchProducts = async () => {
    try {
     
      const cashier = JSON.parse(localStorage.getItem("cashier"));
      if (!cashier || !cashier.branchCode) {
        throw new Error("No branch code found");
        console.log(cashier);
      }
     

      const response = await axios.post("/api/products/getProductByBranchCode", null, {
        params: {
          branchCode: cashier.branchCode,
        },
      });
      console.log("Raw product data:", response.data);
      
      if (!Array.isArray(response.data)) {
        throw new Error("Expected an array of products");
      }
      // Handle base64 image data directly
      const productsWithImages = response.data.map((product) => ({
        ...product,
        imageUrl: product.image ? `data:image/jpeg;base64,${product.image}` : null,
      }));

      console.log("Products with images:", productsWithImages);

      setProducts(productsWithImages);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching products:", error);
      setError("Failed to load products");
      setLoading(false);
    }
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleAddToCart = (product) => {
    setCart((prevCart) => {
      const existingProduct = prevCart.find((item) => item.id === product.id);
      if (existingProduct) {
        if(existingProduct.quantity < product.quantity) {
        return prevCart.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        );
        } else{
          alert("Cannot add more than available quantity");
          return prevCart;
        }
      } else {
        return [...prevCart, { ...product, quantity: 1 }];
      }
    });
  };

  const handleRemoveFromCart = (product) => {
    setCart((prevCart) => {
      const existingProduct = prevCart.find((item) => item.id === product.id);
      if (existingProduct) {
        if (existingProduct.quantity === 1) {
          return prevCart.filter((item) => item.id !== product.id);
        } else {
          return prevCart.map((item) =>
            item.id === product.id ? { ...item, quantity: item.quantity - 1 } : item
          );
        }
      } else {
        return prevCart;
      }
    });
  };

  const handleCheckout = async () => {
    try {
      const cashier = JSON.parse(localStorage.getItem("cashier"));
      if (!cashier || !cashier.branchCode) {
        throw new Error("No branch code found");
      }

      const response = await axios.post("/api/bills/generateBill", cart, {
        params: {
          branchCode: cashier.branchCode,
          cashierName: cashier.name
        },
      });

      console.log("Bill generated:", response.data);
    
      setCart([]);
      alert("Bill generated successfully!");

      // Prepare receipt data
      const receiptData = {
        cashierName: cashier.name,
        billNumber: response.data.billNumber,
        date: new Date().toLocaleString(),
        items: cart.map(item => ({
          name: item.name,
          quantity: item.quantity,
          cost: item.salePrice * item.quantity
        })),
        totalBill: cart.reduce((total, item) => total + item.salePrice * item.quantity, 0),
        gst: cart.reduce((total, item) => total + item.salePrice * item.quantity, 0) * 0.17
      };

      setReceiptData(receiptData);
      setReceiptOpen(true);
      console.log(cashier);
      fetchProducts();
    } catch (error) {
      console.error("Error generating bill:", error);
      setError("Failed to generate bill");
    }
  };
  

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const totalAmount = cart.reduce((total, item) => total + item.salePrice * item.quantity, 0);

  if (loading)
    return <SplashScreen />;

  if (error)
    return (
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
        }}
      >
        <Typography color="error">{error}</Typography>
      </Box>
    );

  return (
    <Box
      sx={{
        display: "flex",
        height: "100vh",
        background: "linear-gradient(135deg, #f3f4f6, #e0e7ff)",
      }}
    >
      

      {/* Product Search and List Section */}
      <Box
        sx={{
          width: "70%",
          p: 4,
          background: "#ffffff",
          borderTopLeftRadius: "10px",
          borderBottomLeftRadius: "10px",
          boxShadow: "5px 0 10px rgba(0,0,0,0.1)",
          display: "flex",
          flexDirection: "column",
        }}
      >
        <Box sx={{ display: "flex", flexDirection: "row", alignItems: "center", p: 0, m: 0, width: '100%' }}>
          <Box sx={{ display: 'flex', justifyContent: 'flex-start', p: 0 }}>
            <ChangePasswordModal role="cashier" />
          </Box>
          <Box sx={{ flexGrow: 1, ml: 2 }}>
            <TextField
              fullWidth
              label="Search Products"
              variant="outlined"
              value={searchTerm}
              onChange={handleSearchChange}
              sx={{
                mb: 4,
                "& .MuiOutlinedInput-root": {
                  borderRadius: "20px",
                },
              }}
            />
          </Box>
        </Box>
        <Box
          sx={{
            flexGrow: 1,
            overflow: "auto",
            maxHeight: "calc(100vh - 150px)", // Adjust height to fit the screen
          }}
        >
        <Grid2 container spacing={4}>
          {filteredProducts.map((product) => (
            <Grid2 item="true" xs={12} sm={6} md={4} key={product.id}>
              <Card
                sx={{
                  height: 280,
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                  borderRadius: "15px",
                  boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
                  transition: "transform 0.3s",
                  "&:hover": {
                    transform: "scale(1.05)",
                  },
                }}
              >
                <CardMedia
                  component="img"
                  height="90"
                  image={product.imageUrl || "placeholder.jpg"}
                  alt={product.name}
                  sx={{ borderRadius: "15px 15px 0 0" }}
                />
                <CardContent sx={{ flexGrow: 1, textAlign: "center" }}>
                  <Typography variant="h6">{product.name}</Typography>
                  <Typography variant="body2" color="text.secondary">
                    ${product.salePrice}
                  </Typography>
                  <Typography variant="body2">Stock: {product.quantity}</Typography>
                </CardContent>
                <Box
                  sx={{
                    display: "flex",
                    justifyContent: "space-around",
                    p: 2,
                    borderTop: "1px solid #f3f4f6",
                  }}
                >
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleAddToCart(product)}
                    sx={{ borderRadius: "20px" }}
                  >
                    +
                  </Button>
                  <Button
                    variant="contained"
                    color="error"
                    onClick={() => handleRemoveFromCart(product)}
                    sx={{ borderRadius: "20px" }}
                  >
                    -
                  </Button>
                </Box>
              </Card>
            </Grid2>
          ))}
        </Grid2>
        </Box>
      </Box>

      {/* Cart Section */}
      <Box
        sx={{
          width: "30%",
          p: 4,
          background: "#f3f4f6",
          color: "#black",
          borderTopRightRadius: "10px",
          borderBottomRightRadius: "10px",
        }}
      >
        
        <Typography variant="h6" gutterBottom>
          Shopping Cart
        </Typography>
        <List>
          {cart.map((item) => (
            <ListItem key={item.id} sx={{ py: 1 }}>
              <ListItemText
                primary={
                  <Typography variant="body1" color="black">
                    {item.name} x{item.quantity}
                  </Typography>
                }
              />
              <Typography variant="body2">${(item.salePrice * item.quantity).toFixed(2)}</Typography>
            </ListItem>
          ))}
        </List>
        <Divider sx={{ my: 2, background: "#64748b" }} />
        <Box sx={{ display: "flex", justifyContent: "space-between" }}>
          <Typography variant="h6" color="black">
            Total:
          </Typography>
          <Typography variant="h6" color="black">
            ${totalAmount.toFixed(2)}
          </Typography>
        </Box>
        <Button
          variant="contained"
          color="success"
          fullWidth
          sx={{
            mt: 4,
            borderRadius: "20px",
            background: "#10b981",
            "&:hover": {
              background: "#059669",
            },
          }}
          onClick={handleCheckout}
        >
          Checkout
        </Button>
      </Box>
       {/* Receipt Modal */}
       {receiptData && (
        <ReceiptModal
          open={receiptOpen}
          onClose={() => setReceiptOpen(false)}
          receiptData={receiptData}
        />
      )}
    </Box>
  );
};

export default CashierPage;
