import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Box, Button, Typography, createTheme, ThemeProvider } from '@mui/material';
import { motion } from 'framer-motion';
import { styled } from '@mui/system';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import WorkIcon from '@mui/icons-material/Work';
import PointOfSaleIcon from '@mui/icons-material/PointOfSale';

// Theme Configuration matching your Dashboard theme
const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#FFD700',
    },
    background: {
      default: '#001F54',
      paper: '#012A63',
    },
  },
  typography: {
    fontFamily: 'Roboto, Arial',
    fontWeightBold: 600,
  },
});

// Styled Components
const StyledButton = styled(motion.div)({
  margin: '20px',
  width: '300px',
});

const buttonVariants = {
  hover: {
    scale: 1.05,
    transition: {
      duration: 0.2,
      ease: 'easeInOut',
    },
  },
};

export default function RoleSelection() {
  const navigate = useNavigate();

  const handleRoleClick = (role) => {
    navigate('/login', { state: { role } });
  };

  return (
    <ThemeProvider theme={darkTheme}>
      <Box
        sx={{
          minHeight: '100vh',
          backgroundColor: 'background.default',
          padding: '40px',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          <Typography
            variant="h4"
            sx={{
              color: 'primary.main',
              marginBottom: '40px',
              textAlign: 'center',
            }}
          >
            Select Role to Login
          </Typography>
        </motion.div>

        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            gap: '20px',
          }}
        >
          <StyledButton
            variants={buttonVariants}
            whileHover="hover"
            whileTap={{ scale: 0.95 }}
          >
            <Button
              variant="contained"
              fullWidth
              startIcon={<AdminPanelSettingsIcon />}
              onClick={() => handleRoleClick('superadmin')}
              sx={{
                backgroundColor: '#FFD700',
                color: '#001F54',
                padding: '15px 30px',
                '&:hover': {
                  backgroundColor: '#FFE55C',
                },
              }}
            >
              Login as Super Admin
            </Button>
          </StyledButton>

          <StyledButton
            variants={buttonVariants}
            whileHover="hover"
            whileTap={{ scale: 0.95 }}
          >
            <Button
              variant="contained"
              fullWidth
              startIcon={<WorkIcon />}
              onClick={() => handleRoleClick('branchmanager')}
              sx={{
                backgroundColor: '#FFD700',
                color: '#001F54',
                padding: '15px 30px',
                '&:hover': {
                  backgroundColor: '#FFE55C',
                },
              }}
            >
              Login as Branch Manager
            </Button>
          </StyledButton>

          <StyledButton
            variants={buttonVariants}
            whileHover="hover"
            whileTap={{ scale: 0.95 }}
          >
            <Button
              variant="contained"
              fullWidth
              startIcon={<PointOfSaleIcon />}
              onClick={() => handleRoleClick('cashier')}
              sx={{
                backgroundColor: '#FFD700',
                color: '#001F54',
                padding: '15px 30px',
                '&:hover': {
                  backgroundColor: '#FFE55C',
                },
              }}
            >
              Login as Cashier
            </Button>
          </StyledButton>

          <StyledButton
            variants={buttonVariants}
            whileHover="hover"
            whileTap={{ scale: 0.95 }}
          >
            <Button
              variant="contained"
              fullWidth
              startIcon={<WorkIcon />}
              onClick={() => handleRoleClick('dataentryoperator')}
              sx={{
                backgroundColor: '#FFD700',
                color: '#001F54',
                padding: '15px 30px',
                '&:hover': {
                  backgroundColor: '#FFE55C',
                },
              }}
            >
              Login as Data Entry Operator
            </Button>
          </StyledButton>
        </Box>
      </Box>
    </ThemeProvider>
  );
}