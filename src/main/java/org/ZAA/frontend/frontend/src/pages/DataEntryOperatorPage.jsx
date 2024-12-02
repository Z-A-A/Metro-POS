import React, { useState, useEffect } from 'react';
import { Box, Button, Typography, InputBase, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from '@mui/material';
import { Add, Delete, Search } from '@mui/icons-material';
import axios from 'axios';

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
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    vendorID: '',
    vendorName: '',
    vendorAddress: '',
    vendorPhone: '',
    branchCode: ''
  });

  useEffect(() => {
    fetchVendors();
  }, []);

  const fetchVendors = async () => {
    try {
      const response = await axios.get('/api/vendors/getAllVendors');
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

  const handleVendorClick = (vendor) => {
    setSelectedVendor(vendor);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const validateForm = () => {
    const { vendorID, vendorName, vendorAddress, vendorPhone, branchCode } = formData;
    return vendorID && vendorName && vendorAddress && vendorPhone && branchCode;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
  
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
      console.log(error.response?.data || 'Error creating vendor');
    }
  };

  return (
    <div>
      <Sidebar>
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

      {/* Content */}
      <Box sx={{ padding: '16px' }}>
        {selectedVendor ? (
          <>
            {/* Render selected vendor details */}
          </>
        ) : (
          <Typography variant="h6">Select a vendor to view details</Typography>
        )}
      </Box>

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
    </div>
  );
};

export default DataEntryOperatorPage;