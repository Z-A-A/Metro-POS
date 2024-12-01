import React from 'react';
import { useState } from 'react';
import { Box, Button, Typography, createTheme, ThemeProvider } from '@mui/material';
import { motion } from 'framer-motion';
import { styled } from '@mui/system';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import WorkIcon from '@mui/icons-material/Work';
import PointOfSaleIcon from '@mui/icons-material/PointOfSale';
import DashboardIcon from '@mui/icons-material/Dashboard'; 
import StaffRegistrationModal from '../components/StaffRegistrationModal';


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

const SuperAdmin = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const [selectedRole, setSelectedRole] = useState('');
    

  const buttonVariants = {
    hover: {
      scale: 1.05,
      transition: {
        duration: 0.2,
        ease: 'easeInOut',
      },
    },
  };
  const handleRoleClick = (role) => {
    setSelectedRole(role);
    setModalOpen(true);
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
           Welcome, Super Admin
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
              startIcon={<PersonAddIcon />}
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
              Add Branch Manager
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
              Add Data Entry Operator
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
              Add Cashier
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
              startIcon={<DashboardIcon />}
              sx={{
                backgroundColor: '#FFD700',
                color: '#001F54',
                padding: '15px 30px',
                '&:hover': {
                  backgroundColor: '#FFE55C',
                },
              }}
            >
              Dashboard
            </Button>
          </StyledButton>
          <StaffRegistrationModal 
            open={modalOpen}
            handleClose={() => setModalOpen(false)}
            staffRole={selectedRole}
            />
        </Box>
      </Box>
    </ThemeProvider>
  );
};

export default SuperAdmin;