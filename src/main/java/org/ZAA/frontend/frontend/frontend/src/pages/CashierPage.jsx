import React, { useState } from 'react';
import laysMasala from '../assets/laysmasala.jpg';
import laysSalted from '../assets/layssalted.jpg';
import laysYoghurt from '../assets/laysyoghurt.jpg';
import { Box, Grid2, TextField, Card, CardContent, Typography, Button, List, ListItem, ListItemText, Divider, CardMedia } from '@mui/material';

const products = [
  { id: 1, name: 'Lays Masala', price: 10, category: 'junk food', currentStock: 10, image: laysMasala },
  { id: 2, name: 'Lays Salted', price: 20, category: 'junk food', currentStock: 10, image: laysSalted },
  { id: 3, name: 'Lays Yoghurt and Herb', price: 30, category: 'junk food', currentStock: 10, image: laysYoghurt },
  // Add more products as needed
];

const CashierPage = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [cart, setCart] = useState([]);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleAddToCart = (product) => {
    setCart((prevCart) => {
      const existingProduct = prevCart.find((item) => item.id === product.id);
      if (existingProduct) {
        return prevCart.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        return [...prevCart, { ...product, quantity: 1 }];
      }
    });
  };

  const handleRemoveFromCart = (product) => {
    setCart((prevCart) => {
      const existingProduct = prevCart.find((item) => item.id === product.id);
      if (existingProduct.quantity === 1) {
        return prevCart.filter((item) => item.id !== product.id);
      } else {
        return prevCart.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity - 1 } : item
        );
      }
    });
  };

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const totalAmount = cart.reduce((total, item) => total + item.price * item.quantity, 0);

  return (
    <>
      <Box sx={{ display: 'flex', height: '100vh' }}>
        <Box sx={{ width: '70%', p: 2 }}>
          <TextField
            fullWidth
            label="Search Products"
            variant="outlined"
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <Grid2 container spacing={2} sx={{ mt: 2 }}>
            {filteredProducts.map((product) => (
              <Grid2 item xs={4} key={product.id}>
                <Card sx={{ width: 200, height: 300, display: 'flex', flexDirection: 'column', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)' }}>
                  <CardMedia
                    component="img"
                    height="140"
                    image={product.image}
                    alt={product.name}
                    sx={{ objectFit: 'cover' }}
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography variant="body1" sx={{ fontWeight: 'bold' }}>{product.name}</Typography>
                    <Typography variant="body2" sx={{ fontWeight: 'bold' }}>${product.price}</Typography>
                    <Typography variant="body2" sx={{ fontWeight: 'bold' }}>Stock: {product.currentStock}</Typography>
                    <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 2 }}>
                      <Button variant="contained" color="primary" onClick={() => handleAddToCart(product)}>
                        +
                      </Button>
                      <Button variant="contained" color="secondary" onClick={() => handleRemoveFromCart(product)}>
                        -
                      </Button>
                    </Box>
                  </CardContent>
                </Card>
              </Grid2>
            ))}
          </Grid2>
        </Box>
        <Box sx={{ width: '30%', p: 2, borderLeft: '1px solid #f3f4f6', borderRadius: '8px', backgroundColor: '#ffffff', boxShadow: '0 1px 3px rgba(0, 0, 0, 0.1)' }}>
          <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
            <img src="https://via.placeholder.com/50" alt="Logo" style={{ marginRight: '10px' }} />
            <Typography variant="h6" sx={{ fontWeight: 'bold' }}>Receipt</Typography>
          </Box>
          <List>
            {cart.map((item) => (
              <ListItem key={item.id} sx={{ display: 'flex', justifyContent: 'space-between' }}>
                <ListItemText
                  primary={<Typography variant="body2" sx={{ fontWeight: 'bold' }}>{item.name} x{item.quantity}</Typography>}
                />
                <Typography variant="body2" sx={{ fontWeight: 'bold' }}>${item.price * item.quantity}</Typography>
              </ListItem>
            ))}
          </List>
          <Divider sx={{ my: 2 }} />
          <Box sx={{ display: 'flex', justifyContent: 'space-between', fontWeight: 'bold', backgroundColor: '#f3f4f6', p: 1, borderRadius: '4px' }}>
            <Typography variant="body1">Total:</Typography>
            <Typography variant="body1">${totalAmount}</Typography>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default CashierPage;