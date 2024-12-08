import React, { useState } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, TextField, Button, Box, Alert } from '@mui/material';
import axios from 'axios';

const BranchCreationModal = ({ open, handleClose }) => {
  const [branchCode, setBranchCode] = useState('');
  const [name, setName] = useState('');
  const [city, setCity] = useState('');
  const [isActive, setIsActive] = useState(true);
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleCreateBranch = async () => {
    try {
      const response = await axios.post('/api/createBranch', null, {
        params: {
          branchCode,
          name,
          city,
          isActive,
          address,
          phone,
        },
      });

      if (response.data) {
        setSuccess('Branch created successfully!');
        setError('');
        setBranchCode('');
        setName('');
        setCity('');
        setIsActive(true);
        setAddress('');
        setPhone('');
      } else {
        setError('Failed to create branch. Please try again.');
        setSuccess('');
      }
    } catch (error) {
      console.error('Error creating branch:', error);
      setError(error.response?.data?.message || 'Failed to create branch');
      setSuccess('');
    }
  };

  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Create Branch</DialogTitle>
      <DialogContent>
        {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
        {success && <Alert severity="success" sx={{ mb: 2 }}>{success}</Alert>}
        <Box
          component="form"
          sx={{
            '& .MuiTextField-root': {
              my: 1,
              width: '100%',
            },
          }}
        >
          <TextField
            label="Branch Code"
            variant="outlined"
            value={branchCode}
            onChange={(e) => setBranchCode(e.target.value)}
            fullWidth
          />
          <TextField
            label="Name"
            variant="outlined"
            value={name}
            onChange={(e) => setName(e.target.value)}
            fullWidth
          />
          <TextField
            label="City"
            variant="outlined"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            fullWidth
          />
          <TextField
            label="Address"
            variant="outlined"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            fullWidth
          />
          <TextField
            label="Phone"
            variant="outlined"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            fullWidth
          />
          <TextField
            label="Active"
            variant="outlined"
            value={isActive}
            onChange={(e) => setIsActive(e.target.value === 'true')}
            fullWidth
          />
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="secondary">
          Cancel
        </Button>
        <Button onClick={handleCreateBranch} color="primary">
          Create Branch
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default BranchCreationModal;