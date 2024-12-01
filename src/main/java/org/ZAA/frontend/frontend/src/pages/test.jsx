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
} from '@mui/material';
import { motion, AnimatePresence } from 'framer-motion';
import { styled } from '@mui/system';

export default function DataEntryOperatorPage() {
// Styled components with enhanced aesthetics
const StyledBox = styled(Box)({
  display: 'flex',
  height: '100vh',
  backgroundColor: '#f8fafc',
  overflow: 'hidden',
});

const Sidebar = styled(Box)({
  width: '300px',
  padding: '24px',
  borderRight: '1px solid rgba(0, 0, 0, 0.08)',
  backgroundColor: '#ffffff',
  boxShadow: '4px 0 12px rgba(0, 0, 0, 0.03)',
  display: 'flex',
  flexDirection: 'column',
});

const Content = styled(Box)({
  flex: 1,
  padding: '32px',
  backgroundColor: '#ffffff',
  overflowY: 'auto',
});

const vendorsList = styled(List)({
  flex: 1,
  overflowY: 'auto',
  '& .MuiListItem-root': {
    borderRadius: '12px',
    marginBottom: '8px',
    transition: 'all 0.2s ease',
    '&:hover': {
      backgroundColor: '#f1f5f9',
      transform: 'translateX(4px)',
    },
  },
});

const StyledCard = styled(motion(Card))({
  width: '240px',
  height: '340px',
  borderRadius: '16px',
  overflow: 'hidden',
  backgroundColor: '#ffffff',
  transition: 'all 0.3s ease-in-out',
  border: '1px solid rgba(0, 0, 0, 0.06)',
  '&:hover': {
    transform: 'translateY(-8px)',
    boxShadow: '0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)',
  },
});

const StyledButton = styled(Button)({
  borderRadius: '12px',
  padding: '12px 24px',
  textTransform: 'none',
  fontWeight: 600,
  boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)',
  transition: 'all 0.2s ease',
  '&:hover': {
    transform: 'translateY(-2px)',
    boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05)',
  },
});

// Animation variants
const containerVariants = {
  hidden: { opacity: 0 },
  show: {
    opacity: 1,
    transition: {
      staggerChildren: 0.1,
    },
  },
};

const itemVariants = {
  hidden: { opacity: 0, y: 20 },
  show: {
    opacity: 1,
    y: 0,
    transition: {
      duration: 0.4,
      ease: 'easeOut',
    },
  },
};

// Update the component's return statement with enhanced styling
return (
  <StyledBox>
    <Sidebar>
      <Typography 
        variant="h5" 
        sx={{ 
          mb: 4, 
          fontWeight: 700, 
          color: '#1e293b',
          letterSpacing: '-0.5px' 
        }}
      >
        Vendors
      </Typography>
      <vendors>
        <AnimatePresence>
          {vendorsList.map((vendor) => (
            <motion.div
              key={vendor.id}
              initial={{ opacity: 0, x: -20 }}
              animate={{ opacity: 1, x: 0 }}
              exit={{ opacity: 0, x: -20 }}
              transition={{ duration: 0.2 }}
            >
              <ListItem
                button
                onClick={() => handleVendorClick(vendor)}
                sx={{
                  py: 2,
                  px: 3,
                }}
              >
                <ListItemText 
                  primary={vendor.name}
                  primaryTypographyProps={{
                    fontWeight: 600,
                    color: '#334155',
                  }}
                />
              </ListItem>
            </motion.div>
          ))}
        </AnimatePresence>
      </vendors>
      <Box sx={{ display: 'flex', gap: 2, mt: 2 }}>
        <StyledButton 
          variant="contained" 
          fullWidth
          sx={{ 
            bgcolor: '#0ea5e9',
            '&:hover': { bgcolor: '#0284c7' },
          }}
        >
          Add Vendor
        </StyledButton>
        <StyledButton 
          variant="contained"
          fullWidth
          sx={{ 
            bgcolor: '#ef4444',
            '&:hover': { bgcolor: '#dc2626' },
          }}
        >
          Remove Vendor
        </StyledButton>
      </Box>
    </Sidebar>

    <Content>
      {selectedVendor ? (
        <motion.div
          initial="hidden"
          animate="show"
          variants={containerVariants}
        >
          <Typography 
            variant="h4" 
            sx={{ 
              mb: 4, 
              fontWeight: 700,
              color: '#1e293b',
              letterSpacing: '-0.5px'
            }}
          >
            {selectedVendor.name} - Products
          </Typography>
          <Grid container spacing={3}>
            {selectedVendor.products.map((product, index) => (
              <Grid item key={index}>
                <motion.div variants={itemVariants}>
                  <StyledCard>
                    <CardMedia
                      component="img"
                      height="180"
                      image={product.image}
                      alt={product.name}
                      sx={{ 
                        objectFit: 'cover',
                        borderBottom: '1px solid rgba(0, 0, 0, 0.06)'
                      }}
                    />
                    <CardContent sx={{ p: 2.5 }}>
                      <Typography 
                        variant="h6" 
                        sx={{ 
                          fontWeight: 600,
                          mb: 1,
                          color: '#1e293b'
                        }}
                      >
                        {product.name}
                      </Typography>
                      <Typography 
                        variant="body1" 
                        sx={{ 
                          color: '#64748b',
                          mb: 0.5 
                        }}
                      >
                        ${product.price}
                      </Typography>
                      <Typography 
                        variant="body2" 
                        sx={{ 
                          color: '#94a3b8'
                        }}
                      >
                        Stock: {product.quantity}
                      </Typography>
                    </CardContent>
                  </StyledCard>
                </motion.div>
              </Grid>
            ))}
          </Grid>
          <Box sx={{ display: 'flex', gap: 2, mt: 4 }}>
            <StyledButton
              variant="contained"
              onClick={handleAddProductClick}
              sx={{ 
                bgcolor: '#0ea5e9',
                '&:hover': { bgcolor: '#0284c7' },
              }}
            >
              Add Product
            </StyledButton>
            <StyledButton
              variant="contained"
              sx={{ 
                bgcolor: '#ef4444',
                '&:hover': { bgcolor: '#dc2626' },
              }}
            >
              Remove Product
            </StyledButton>
          </Box>
        </motion.div>
      ) : (
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.4 }}
        >
          <Typography 
            variant="h5" 
            sx={{ 
              color: '#94a3b8',
              fontWeight: 600 
            }}
          >
            Select a vendor to view products
          </Typography>
        </motion.div>
      )}
    </Content>

    <Dialog 
      open={open} 
      onClose={handleClose}
      PaperProps={{
        sx: {
          borderRadius: '16px',
          boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.25)',
          width: '400px',
        }
      }}
    >
      <DialogTitle sx={{ 
        pb: 2,
        fontWeight: 700,
        color: '#1e293b'
      }}>
        Add New Product
      </DialogTitle>
      <DialogContent sx={{ pt: 2 }}>
        <TextField
          autoFocus
          margin="dense"
          label="Product Name"
          type="text"
          fullWidth
          name="name"
          value={newProduct.name}
          onChange={handleProductChange}
          sx={{ mb: 2 }}
        />
        <TextField
          margin="dense"
          label="Price"
          type="number"
          fullWidth
          name="price"
          value={newProduct.price}
          onChange={handleProductChange}
          sx={{ mb: 2 }}
        />
        <TextField
          margin="dense"
          label="Quantity"
          type="number"
          fullWidth
          name="quantity"
          value={newProduct.quantity}
          onChange={handleProductChange}
          sx={{ mb: 3 }}
        />
        <StyledButton
          variant="outlined"
          component="label"
          fullWidth
          sx={{ 
            color: '#0ea5e9',
            borderColor: '#0ea5e9',
            '&:hover': {
              borderColor: '#0284c7',
              backgroundColor: 'rgba(14, 165, 233, 0.04)',
            }
          }}
        >
          Upload Image
          <input type="file" hidden onChange={handleImageChange} />
        </StyledButton>
      </DialogContent>
      <DialogActions sx={{ p: 3 }}>
        <StyledButton 
          onClick={handleClose}
          sx={{ 
            color: '#64748b',
            '&:hover': { backgroundColor: '#f1f5f9' }
          }}
        >
          Cancel
        </StyledButton>
        <StyledButton
          onClick={handleAddProduct}
          variant="contained"
          sx={{ 
            bgcolor: '#0ea5e9',
            '&:hover': { bgcolor: '#0284c7' },
          }}
        >
          Add Product
        </StyledButton>
      </DialogActions>
    </Dialog>
  </StyledBox>
);
}