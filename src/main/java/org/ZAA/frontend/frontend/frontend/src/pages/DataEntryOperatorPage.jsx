import React, { useState } from 'react';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardMedia,
  Grid,
  List,
  ListItem,
  ListItemText,
  TextField,
  Typography,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
  InputBase,
} from '@mui/material';
import { Add, Delete, Search } from '@mui/icons-material';
import { motion } from 'framer-motion';
import { styled } from '@mui/system';

// Sample vendors array
const vendors = [
  { id: 1, name: 'Vendor 1', products: [] },
  { id: 2, name: 'Vendor 2', products: [] },
];

// Styled components
const StyledBox = styled(Box)({
  display: 'flex',
  height: '100vh',
  background: 'linear-gradient(135deg, #f3f4f6, #e3e8f0)',
});

const Sidebar = styled(Box)({
  width: '20%',
  padding: '16px',
  background: '#ffffff',
  borderRadius: '16px',
  boxShadow: '0 8px 12px rgba(0, 0, 0, 0.1)',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
});

const SearchBar = styled(Box)({
  width: '100%',
  display: 'flex',
  alignItems: 'center',
  background: '#f1f5f9',
  padding: '8px 12px',
  borderRadius: '12px',
  marginBottom: '16px',
  boxShadow: 'inset 0 1px 2px rgba(0, 0, 0, 0.05)',
});

const VendorCard = styled(Box)({
  width: '100%',
  margin: '8px 0',
  padding: '12px',
  borderRadius: '12px',
  background: '#f9fafb',
  boxShadow: '0 2px 4px rgba(0, 0, 0, 0.05)',
  transition: 'transform 0.2s, box-shadow 0.2s',
  cursor: 'pointer',
  '&:hover': {
    transform: 'translateY(-2px)',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
  },
});

const Content = styled(Box)({
  flex: 1,
  padding: '16px',
  display: 'flex',
  flexDirection: 'column',
  overflowY: 'auto',
});

const ProductCard = styled(Card)({
  width: 240,
  margin: '8px',
  borderRadius: '16px',
  overflow: 'hidden',
  boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
  transition: 'transform 0.3s',
  '&:hover': {
    transform: 'scale(1.05)',
  },
});

const AppHeader = styled(Box)({
  width: '100%',
  padding: '16px',
  background: '#ffffff',
  borderBottom: '1px solid #e5e7eb',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  boxShadow: '0 2px 4px rgba(0, 0, 0, 0.05)',
  position: 'sticky',
  top: 0,
  zIndex: 10,
});

// Component
const DataEntryOperatorPage = () => {
  const [selectedVendor, setSelectedVendor] = useState(null);
  const [open, setOpen] = useState(false);
  const [newProduct, setNewProduct] = useState({ name: '', price: '', quantity: '', image: null });

  const handleVendorClick = (vendor) => {
    setSelectedVendor(vendor);
  };

  const handleAddProductClick = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleProductChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prev) => ({ ...prev, [name]: value }));
  };

  const handleImageChange = (e) => {
    setNewProduct((prev) => ({ ...prev, image: URL.createObjectURL(e.target.files[0]) }));
  };

  const handleAddProduct = () => {
    if (selectedVendor) {
      selectedVendor.products.push(newProduct);
      setSelectedVendor({ ...selectedVendor });
      setOpen(false);
      setNewProduct({ name: '', price: '', quantity: '', image: null });
    }
  };

  return (
    <StyledBox>
      {/* Header */}
      {/* <AppHeader>
        <Typography variant="h5" sx={{ fontWeight: 'bold', color: '#1976d2' }}>
          Data Entry Portal
        </Typography>
      </AppHeader> */}

      {/* Sidebar */}
      <Sidebar>
        <SearchBar>
          <Search sx={{ color: '#9e9e9e', marginRight: '8px' }} />
          <InputBase placeholder="Search Vendors..." fullWidth />
        </SearchBar>
        {vendors.map((vendor) => (
          <VendorCard key={vendor.id} onClick={() => handleVendorClick(vendor)}>
            <Typography variant="body1" sx={{ fontWeight: 'bold', color: '#333' }}>
              {vendor.name}
            </Typography>
          </VendorCard>
        ))}
        <Box sx={{ display: 'flex', gap: '8px', marginTop: '16px' }}>
          <Button variant="contained" color="primary" startIcon={<Add />}>
            Add Vendor
          </Button>
          <Button variant="outlined" color="error" startIcon={<Delete />}>
            Remove Vendor
          </Button>
        </Box>
      </Sidebar>

      {/* Content */}
      <Content>
        {selectedVendor ? (
          <>
            <Typography variant="h5" sx={{ mb: 3, fontWeight: 'bold', color: '#1976d2' }}>
              {selectedVendor.name} - Products
            </Typography>
            <Grid container spacing={3}>
              {selectedVendor.products.map((product, index) => (
                <Grid item key={index}>
                  <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                  >
                    <ProductCard>
                      <CardMedia
                        component="img"
                        height="140"
                        image={product.image}
                        alt={product.name}
                      />
                      <CardContent>
                        <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                          {product.name}
                        </Typography>
                        <Typography variant="body2">Price: ${product.price}</Typography>
                        <Typography variant="body2">Quantity: {product.quantity}</Typography>
                      </CardContent>
                    </ProductCard>
                  </motion.div>
                </Grid>
              ))}
            </Grid>
            <Box sx={{ display: 'flex', gap: '16px', marginTop: '16px' }}>
              <Button variant="contained" color="primary" onClick={handleAddProductClick}>
                Add Product
              </Button>
              <Button variant="outlined" color="error">
                Remove Product
              </Button>
            </Box>
          </>
        ) : (
          <Typography variant="h6" sx={{ color: '#1976d2' }}>
            Select a vendor to view products
          </Typography>
        )}
      </Content>

      {/* Dialog */}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Add New Product</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Product Name"
            type="text"
            fullWidth
            name="name"
            value={newProduct.name}
            onChange={handleProductChange}
          />
          <TextField
            margin="dense"
            label="Price"
            type="number"
            fullWidth
            name="price"
            value={newProduct.price}
            onChange={handleProductChange}
          />
          <TextField
            margin="dense"
            label="Quantity"
            type="number"
            fullWidth
            name="quantity"
            value={newProduct.quantity}
            onChange={handleProductChange}
          />
          <Button variant="contained" component="label" sx={{ mt: 2 }}>
            Upload Image
            <input type="file" hidden onChange={handleImageChange} />
          </Button>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleAddProduct} color="primary">
            Add Product
          </Button>
        </DialogActions>
      </Dialog>
    </StyledBox>
  );
};

export default DataEntryOperatorPage;
