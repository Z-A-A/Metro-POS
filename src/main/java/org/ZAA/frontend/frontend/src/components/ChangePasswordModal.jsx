import React, { useState } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, TextField, Button, IconButton, Typography } from '@mui/material';
import LockIcon from '@mui/icons-material/Lock';
import axios from 'axios';

const ChangePasswordModal = ({ role }) => {
  const [changePasswordOpen, setChangePasswordOpen] = useState(false);
  const [email, setEmail] = useState('');
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [error, setError] = useState('');

  const handleChangePasswordOpen = () => {
    setChangePasswordOpen(true);
  };

  const handleChangePasswordClose = () => {
    setChangePasswordOpen(false);
  };

  const handleChangePasswordSubmit = async () => {
    try {
      const apiEndpoints = {
        superadmin: '/api/changePassword/superadmin',
        dataentryoperator: '/api/changePassword/dataentryoperator',
        branchmanager: '/api/changePassword/branchmanager',
        cashier: '/api/changePassword/cashier',
      };

      const response = await axios.post(apiEndpoints[role], null, {
        params: {
          email,
          oldPassword,
          newPassword,
        },
      });

      if (response.data) {
        alert('Password changed successfully!');
        handleChangePasswordClose();
      } else {
        setError('Failed to change password. Please check your credentials.');
      }
    } catch (error) {
      console.error('Error changing password:', error);
      setError('Failed to change password. Please try again later.');
    }
  };

  return (
    <>
      <IconButton color="primary" onClick={handleChangePasswordOpen}>
        <LockIcon />
      </IconButton>
      <Dialog open={changePasswordOpen} onClose={handleChangePasswordClose}>
        <DialogTitle>Change Password</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Email"
            type="email"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            margin="dense"
            label="Old Password"
            type="password"
            fullWidth
            value={oldPassword}
            onChange={(e) => setOldPassword(e.target.value)}
          />
          <TextField
            margin="dense"
            label="New Password"
            type="password"
            fullWidth
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
          {error && <Typography color="error">{error}</Typography>}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleChangePasswordClose} color="secondary">
            Cancel
          </Button>
          <Button onClick={handleChangePasswordSubmit} color="primary">
            Change Password
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default ChangePasswordModal;