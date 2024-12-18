import React, { useState, useEffect } from 'react';
import { Box, Button, Typography, InputBase, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Fab, Card, CardContent, CardMedia, Grid2 } from '@mui/material';
import { Add, Delete, Search } from '@mui/icons-material';
import axios from 'axios';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import ChangePasswordModal from '../components/ChangePasswordModal';
import SplashScreen from './SplashScreen';


// Define Sidebar and SearchBar components
const Sidebar = ({ children }) => (
  <Box sx={{ width: '250px', padding: '16px', borderRight: '1px solid #ddd' }}>
    {children}
  </Box>
);

const SearchBar = ({ children }) => (
  <Box sx={{ display: 'flex', alignItems: 'center', marginBottom: '16px' }}>
    {children}
  </Box>
);

const VendorCard = ({ children, onClick }) => (
  <Box sx={{ padding: '8px', borderBottom: '1px solid #ddd', cursor: 'pointer' }} onClick={onClick}>
    {children}
  </Box>
);

const DataEntryOperatorPage = () => {
  const [vendors, setVendors] = useState([]);
  const [selectedVendor, setSelectedVendor] = useState(null);
  const [products, setProducts] = useState([]);
  const [open, setOpen] = useState(false);
  const [productFormOpen, setProductFormOpen] = useState(false);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({
    vendorID: '',
    vendorName: '',
    vendorAddress: '',
    vendorPhone: '',
    branchCode: ''
  });
  const [productFormData, setProductFormData] = useState({
    id: '',
    name: '',
    originalPrice: '',
    salePrice: '',
    priceByUnit: '',
    priceByCarton: '',
    category: '',
    description: '',
    branchCode: '',
    vendorId: '',
    quantity: '',
    image: null
  });
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    
    setTimeout(() => {
      fetchVendors();
      setLoading(false); 
    }, 2000);
  }, []);

  const fetchVendors = async () => {
    try {
      const response = await axios.post('/api/vendors/getAllVendors');
      if (Array.isArray(response.data)) {
        setVendors(response.data);
      } else {
        setVendors([]);
      }
    } catch (error) {
      console.error('Error fetching vendors:', error);
      setVendors([]);
    }
  };

  const fetchProductsByVendor = async (vendor) => {
    try {
      const response = await axios.post('/api/products/getProductByVendorId', null, {
        params: {
          branchCode: vendor.branchCode,
          vendorId: vendor.vendorID
        }
      });

      if (Array.isArray(response.data)) {
        setProducts(response.data);
      } else {
        setProducts([]);
      }
    } catch (error) {
      console.error('Error fetching products:', error);
      setProducts([]);
    }
  };

  const handleVendorClick = (vendor) => {
    setSelectedVendor(vendor);
    fetchProductsByVendor(vendor);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleProductFormOpen = () => {
    setProductFormOpen(true);
  };

  const handleProductFormClose = () => {
    setProductFormOpen(false);
  };

  

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleProductFormChange = (e) => {
    const { name, value } = e.target;
    setProductFormData({
      ...productFormData,
      [name]: value
    });
  };

  const handleImageChange = (e) => {
    setProductFormData({
      ...productFormData,
      image: e.target.files[0]
    });
  };

  const validateForm = () => {
    const { vendorID, vendorName, vendorAddress, vendorPhone, branchCode } = formData;
    return vendorID && vendorName && vendorAddress && vendorPhone && branchCode;
  };

  const validateProductForm = () => {
    const { id, name, originalPrice, salePrice, priceByUnit, priceByCarton, category, description, branchCode, vendorId, quantity, image } = productFormData;
    return id && name && originalPrice && salePrice && priceByUnit && priceByCarton && category && description && branchCode && vendorId && quantity && image;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) {
      setError('All fields are required.');
      return;
    }

    try {
      const params = new URLSearchParams({
        vendorID: formData.vendorID,
        vendorName: formData.vendorName,
        vendorAddress: formData.vendorAddress,
        vendorPhone: formData.vendorPhone,
        branchCode: formData.branchCode
      });

      const response = await axios.post(`/api/vendors/createVendor?${params.toString()}`);

      if (response.data) {
        console.log('Vendor created successfully:', response.data);
        setVendors((prevVendors) => [...prevVendors, response.data]);
        setSuccess('Vendor created successfully!');
        setTimeout(() => {
          handleClose();
          setFormData({
            vendorID: '',
            vendorName: '',
            vendorAddress: '',
            vendorPhone: '',
            branchCode: ''
          });
        }, 1500);
      }
    } catch (error) {
      console.error('Error creating vendor:', error);
      setError(error.response?.data?.message || 'Error creating vendor');
    }
  };

  const handleProductSubmit = async (e) => {
    e.preventDefault();
    console.log('handleProductSubmit called');
    
    if (!validateProductForm()) {
      setError('All fields are required.');
      console.log('Validation failed: All fields are required.');
      return;
    }
  
    try {
      const formData = new FormData();
      formData.append('id', parseInt(productFormData.id, 10));
      formData.append('name', productFormData.name);
      formData.append('originalPrice', parseFloat(productFormData.originalPrice));
      formData.append('salePrice', parseFloat(productFormData.salePrice));
      formData.append('priceByUnit', parseFloat(productFormData.priceByUnit));
      formData.append('priceByCarton', parseFloat(productFormData.priceByCarton));
      formData.append('category', productFormData.category);
      formData.append('description', productFormData.description);
      formData.append('branchCode', productFormData.branchCode);
      formData.append('vendorId', parseInt(productFormData.vendorId, 10));
      formData.append('quantity', parseInt(productFormData.quantity, 10));
      formData.append('image', productFormData.image);
  
      console.log('Sending product data:', formData);
  
      const response = await axios.post('/api/products/createProduct', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
  
      if (response.data) {
        console.log('Product created successfully:', response.data);
        setSuccess('Product created successfully!');
        setTimeout(() => {
          handleProductFormClose();
          setProductFormData({
            id: '',
            name: '',
            originalPrice: '',
            salePrice: '',
            priceByUnit: '',
            priceByCarton: '',
            category: '',
            description: '',
            branchCode: '',
            vendorId: '',
            quantity: '',
            image: null
          });
        }, 1500);
      }
    } catch (error) {
      console.error('Error creating product:', error);
      setError(error.response?.data?.message || 'Error creating product');
    }
  };

  const handleIncreaseQuantity = async (product) => {
    try {
      const newQuantity = product.quantity + 1;
      const response = await axios.post('/api/products/updateProductQuantity', null, {
        params: {
          id: product.id,
          quantity: newQuantity
        }
      });

      if (response.data) {
        setProducts((prevProducts) =>
          prevProducts.map((p) =>
            p.id === product.id ? { ...p, quantity: newQuantity } : p
          )
        );
      }
    } catch (error) {
      console.error('Error updating product quantity:', error);
    }
  };

  const handleDecreaseQuantity = async (product) => {
    if (product.quantity > 0) {
      try {
        const newQuantity = product.quantity - 1;
        const response = await axios.post('/api/products/updateProductQuantity', null, {
          params: {
            id: product.id,
            quantity: newQuantity
          }
        });

        if (response.data) {
          setProducts((prevProducts) =>
            prevProducts.map((p) =>
              p.id === product.id ? { ...p, quantity: newQuantity } : p
            )
          );
        }
      } catch (error) {
        console.error('Error updating product quantity:', error);
      }
    }
  };



  return (
    <div>
      {loading? ( <SplashScreen /> ) : (
    <Box sx={{display:'flex', height:'100vh'}}>
      <Sidebar sx={{height:'100vh'}}>
        <SearchBar>
          <Search sx={{ color: '#9e9e9e', marginRight: '8px' }} />
          <InputBase placeholder="Search Vendors..." fullWidth />
        </SearchBar>
        {Array.isArray(vendors) && vendors.map((vendor) => (
          <VendorCard key={vendor.vendorID} onClick={() => handleVendorClick(vendor)}>
            <Typography variant="body1" sx={{ fontWeight: 'bold', color: '#333' }}>
              {vendor.vendorName}
            </Typography>
          </VendorCard>
        ))}
        <Box sx={{ display: 'flex', gap: '8px', marginTop: '16px' }}>
          <Button variant="contained" color="primary" startIcon={<Add />} onClick={handleOpen}>
            Add Vendor
          </Button>
          <Button variant="outlined" color="error" startIcon={<Delete />}>
            Remove Vendor
          </Button>
          
        </Box>
      </Sidebar>

      {/*Main Content */}
      <Box sx={{ flex:1, padding: '10px' }}>
      <Box sx={{ display: 'flex', justifyContent: 'flex-end', p: 0, m :0 }}>
        <ChangePasswordModal role="dataentryoperator" />
      </Box>
        {selectedVendor ? (
          <>
            <Typography variant="h6">Products for {selectedVendor.vendorName}</Typography>
            <Grid2 container spacing={2} sx={{ mt: 2 }}>
              {products.map((product) => (
                <Grid2 item="true" xs={4} key={product.id}>
                  <Card sx={{ width: 200, height: 300, display: 'flex', flexDirection: 'column' }}>
                    <CardMedia
                      component="img"
                      height="140"
                      image={product.image ? `data:image/jpeg;base64,${product.image}` : 'placeholder.jpg'}
                      alt={product.name}
                      sx={{ objectFit: 'cover' }}
                    />
                    <CardContent sx={{ flexGrow: 1 }}>
                      <Typography variant="body1" sx={{ fontWeight: 'bold' }}>{product.name}</Typography>
                      <Typography variant="body2" sx={{ fontWeight: 'bold' }}>${product.salePrice}</Typography>
                      <Typography variant="body2">Stock: {product.quantity}</Typography>
                      <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 2 }}>
                        <Button variant="contained" color="primary" onClick={() => handleIncreaseQuantity(product)}>
                          +
                        </Button>
                        <Button variant="contained" color="secondary" onClick={() => handleDecreaseQuantity(product)}>
                          -
                        </Button>
                      </Box>
                    </CardContent>
                  </Card>
                </Grid2>
              ))}
            </Grid2>
          </>
        ) : (
          <Typography variant="h6">Select a vendor to view details</Typography>
        )}
      </Box>
      </Box>
      )}

      {/* Add Vendor Dialog */}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Add Vendor</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            name="vendorID"
            label="Vendor ID"
            type="number"
            fullWidth
            variant="standard"
            value={formData.vendorID}
            onChange={handleChange}
          />
          <TextField
            margin="dense"
            name="vendorName"
            label="Vendor Name"
            type="text"
            fullWidth
            variant="standard"
            value={formData.vendorName}
            onChange={handleChange}
          />
          <TextField
            margin="dense"
            name="vendorAddress"
            label="Vendor Address"
            type="text"
            fullWidth
            variant="standard"
            value={formData.vendorAddress}
            onChange={handleChange}
          />
          <TextField
            margin="dense"
            name="vendorPhone"
            label="Vendor Phone"
            type="text"
            fullWidth
            variant="standard"
            value={formData.vendorPhone}
            onChange={handleChange}
          />
          <TextField
            margin="dense"
            name="branchCode"
            label="Branch Code"
            type="text"
            fullWidth
            variant="standard"
            value={formData.branchCode}
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit}>Add</Button>
        </DialogActions>
      </Dialog>

      {/* Add Product Dialog */}
      <Dialog open={productFormOpen} onClose={handleProductFormClose}>
        <DialogTitle>Add Product</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            name="id"
            label="Product ID"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.id}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="name"
            label="Product Name"
            type="text"
            fullWidth
            variant="standard"
            value={productFormData.name}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="originalPrice"
            label="Original Price"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.originalPrice}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="salePrice"
            label="Sale Price"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.salePrice}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="priceByUnit"
            label="Price by Unit"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.priceByUnit}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="priceByCarton"
            label="Price by Carton"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.priceByCarton}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="category"
            label="Category"
            type="text"
            fullWidth
            variant="standard"
            value={productFormData.category}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="description"
            label="Description"
            type="text"
            fullWidth
            variant="standard"
            value={productFormData.description}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="branchCode"
            label="Branch Code"
            type="text"
            fullWidth
            variant="standard"
            value={productFormData.branchCode}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="vendorId"
            label="Vendor ID"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.vendorId}
            onChange={handleProductFormChange}
          />
          <TextField
            margin="dense"
            name="quantity"
            label="Quantity"
            type="number"
            fullWidth
            variant="standard"
            value={productFormData.quantity}
            onChange={handleProductFormChange}
          />
          <Box sx={{ mt: 2, mb: 1 }}>
            <input
              accept="image/*"
              style={{ display: 'none' }}
              id="product-image-upload"
              type="file"
              onChange={handleImageChange}
            />
            <label htmlFor="product-image-upload">
              <Button
                variant="outlined"
                component="span"
                startIcon={<CloudUploadIcon />}
                fullWidth
              >
                Upload Product Image
              </Button>
            </label>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleProductFormClose}>Cancel</Button>
          <Button onClick={handleProductSubmit}>Add</Button>
        </DialogActions>
      </Dialog>

      {selectedVendor && (
        <Fab
          color="primary"
          aria-label="add"
          onClick={handleProductFormOpen}
          sx={{ position: 'fixed', bottom: 16, right: 16 }}
        >
          <Add />
        </Fab>
      )}
    </div>
  );
};

export default DataEntryOperatorPage;

