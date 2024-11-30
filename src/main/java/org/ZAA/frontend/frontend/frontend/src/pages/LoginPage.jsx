import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, TextField, Button, Typography, Paper } from '@mui/material';
import { styled } from '@mui/system';

const BackgroundBox = styled(Box)({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '100vh',
  background: 'linear-gradient(135deg, #6e8efb, #a777e3)',
});

const LoginBox = styled(Paper)({
  padding: '40px',
  maxWidth: '400px',
  width: '100%',
  textAlign: 'center',
  borderRadius: '12px',
  boxShadow: '0 4px 20px rgba(0, 0, 0, 0.1)',
});

const StyledTextField = styled(TextField)({
  marginBottom: '20px',
});

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  

  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('/api/login/superadmin', {
        email,
        password
      });
      console.log('Login successful:', response.status);
      console.log(response.data);
    } catch (error) {
      console.error('Login failed:', error);
      if (error.response) {
        console.error('Response data:', error.response.data);
        console.error('Response status:', error.response.status);
        console.error('Response headers:', error.response.headers);
      }
    }
  };

  return (
    <BackgroundBox>
      <LoginBox>
        <Typography variant="h4" component="h1" gutterBottom>
          Welcome Back
        </Typography>
        <Typography variant="body1" gutterBottom>
          Please login to your account
        </Typography>
        <form onSubmit={handleLogin}>
          <StyledTextField
            fullWidth
            label="Email"
            variant="outlined"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <StyledTextField
            fullWidth
            label="Password"
            variant="outlined"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Button
            fullWidth
            variant="contained"
            color="primary"
            size="large"
            type="submit"
            sx={{ marginTop: '20px', padding: '10px 0' }}
          >
            Login
          </Button>
        </form>
        <Typography variant="body2" sx={{ marginTop: '20px' }}>
          Don't have an account? <a href="#">Sign up</a>
        </Typography>
      </LoginBox>
    </BackgroundBox>
  );
};

export default LoginPage;