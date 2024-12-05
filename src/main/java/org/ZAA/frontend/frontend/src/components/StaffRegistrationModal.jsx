import React, { useState } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Box,
  Alert
} from '@mui/material';
import axios from 'axios';

const StaffRegistrationModal = ({ open, handleClose, staffRole }) => {
  const [formData, setFormData] = useState({
    name: '',
    employeeNumber: '',
    email: '',
    password: '', // Added password
    branchCode: '',
    salary: '',
  });

  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const validateForm = () => {
    const requiredFields = ['name', 'email', 'password', 'branchCode', 'salary'];
    for (const field of requiredFields) {
      if (!formData[field]) {
        setError(`${field.charAt(0).toUpperCase() + field.slice(1)} is required`);
        return false;
      }
    }
    return true;
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
    setError('');
    setSuccess('');
  };

  const getEndpoint = () => {
    switch(staffRole) {
      case 'branchmanager': return '/api/signup/branchmanager';
      case 'cashier': return '/api/signup/cashier';
      case 'dataentryoperator': return '/api/signup/dataentryoperator';
      default: return '';
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const params = new URLSearchParams({
        name: formData.name,
        email: formData.email,
        password: formData.password,
        branchCode: formData.branchCode,
        salary: Number(formData.salary)
      });

      const response = await axios.post(`${getEndpoint()}?${params}`);
      
      if (response.data === true) {
        setSuccess('Registration successful!');
        setTimeout(() => {
          handleClose();
          setFormData({
            name: '',
            employeeNumber: '',
            email: '',
            password: '',
            branchCode: '',
            salary: '',
          });
        }, 1500);
      }
    } catch (error) {
      console.error('Registration failed:', error);
      setError(error.response?.data?.message || 'Registration failed');
    }
  };

  return (
    <Dialog 
      open={open} 
      onClose={handleClose}
      PaperProps={{
        sx: {
          backgroundColor: '#012A63',
          color: 'white',
          minWidth: '400px'
        }
      }}
    >
      <DialogTitle sx={{ color: '#FFD700' }}>
        Add New {staffRole.charAt(0).toUpperCase() + staffRole.slice(1)}
      </DialogTitle>
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
            name="name"
            label="Name"
            value={formData.name}
            onChange={handleChange}
            sx={{ input: { color: 'white' } }}
            required
          />
          <TextField
            name="email"
            label="Email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            sx={{ input: { color: 'white' } }}
            required
          />
          <TextField
            name="password"
            label="Password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            sx={{ input: { color: 'white' } }}
            required
          />
          <TextField
            name="branchCode"
            label="Branch Code"
            value={formData.branchCode}
            onChange={handleChange}
            sx={{ input: { color: 'white' } }}
            required
          />
          <TextField
            name="salary"
            label="Salary"
            type="number"
            value={formData.salary}
            onChange={handleChange}
            sx={{ input: { color: 'white' } }}
            required
          />
        </Box>
      </DialogContent>
      <DialogActions sx={{ padding: '20px' }}>
        <Button 
          onClick={handleClose}
          sx={{ color: '#FFD700' }}
        >
          Cancel
        </Button>
        <Button 
         name="Submit"
          onClick={handleSubmit}
          variant="contained"
          sx={{
            backgroundColor: '#FFD700',
            color: '#001F54',
            '&:hover': {
              backgroundColor: '#FFE55C',
            }
          }}
        >
          Add
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default StaffRegistrationModal;