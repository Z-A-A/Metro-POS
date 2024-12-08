import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
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
  const location = useLocation();
  const role = location.state?.role || 'superadmin'; 
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
    console.log('handleLogin called');
    console.log('Email:', email);
    console.log('Password:', password);
    console.log('Role:', role);
  
    try {
      let apiEndpoint = '';
      switch (role) {
        case 'superadmin':
          apiEndpoint = '/api/login/superadmin';
          break;
        case 'branchmanager':
          apiEndpoint = '/api/login/admin';
          break;
        case 'cashier':
          apiEndpoint = '/api/login/cashier';
          break;
        case 'dataentryoperator':
          apiEndpoint = '/api/login/dataentryoperator';
          break;
        default:
          console.log('Invalid role');
          return;
      }
  
      // Use GET request with query parameters
      const response = await axios.get(`${apiEndpoint}?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`);
  
      console.log('Login response:', response);
  
      if (response.status === 200) {
        console.log(`Navigating to ${role} page`);
        switch (role) {
          case 'superadmin':
            navigate('/superadmin');
            break;
          case 'branchmanager':
            navigate('/branchmanager');
            break;
          case 'cashier':
            localStorage.setItem('cashier', JSON.stringify(response.data));
            console.log('Cashier data:', response.data);
            navigate('/cashier');
            break;
          case 'dataentryoperator':
            navigate('/dataentryoperator');
            break;
          default:
            console.log('Invalid role');
        }
      }
    } catch (error) {
      console.error('Login failed:', error);
      if (error.response) {
        console.error('Error details:', error.response.data);
      }
    }
  };

  return (
    <BackgroundBox>
      <LoginBox>
        <Typography variant="h4" component="h1" gutterBottom>
          Login as {role.charAt(0).toUpperCase() + role.slice(1)}
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
      </LoginBox>
    </BackgroundBox>
  );
};

export default LoginPage;