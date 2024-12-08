import React from 'react';
import { Box, Button, Typography } from '@mui/material';
import { motion } from 'framer-motion';
import { styled } from '@mui/system';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PointOfSaleIcon from '@mui/icons-material/PointOfSale';
import DescriptionIcon from '@mui/icons-material/Description';
import ChangePasswordModal from '../components/ChangePasswordModal';
import StaffRegistrationModal from '../components/StaffRegistrationModal';
import { useNavigate } from 'react-router-dom';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import GenerateReport from '../components/generateReport'; 

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

const StyledButton = styled(motion.div)({
  marginBottom: '20px',
  width: '300px',
});

const buttonVariants = {
  hover: { scale: 1.05 },
};

const BranchManagerPage = () => {
  const navigate = useNavigate();
  const [open, setOpen] = React.useState(false);
  const [staffRole, setStaffRole] = React.useState('');

  const handleRoleClick = (role) => {
    setStaffRole(role);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleGenerateReport = async () => {
    try {
      await generateReport();
    } catch (error) {
      console.error('Error generating report:', error);
    }
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
        <Box sx={{ display: 'flex', justifyContent: 'flex-end', p: 2 }}>
          <ChangePasswordModal role="branchmanager" />
        </Box>

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
            Welcome, Branch Manager
          </Typography>
        </motion.div>

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
            startIcon={<PersonAddIcon />}
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
          {/* <Button
            variant="contained"
            fullWidth
            startIcon={<DescriptionIcon />}
            onClick={handleGenerateReport}
            sx={{
              backgroundColor: '#FFD700',
              color: '#001F54',
              padding: '15px 30px',
              '&:hover': {
                backgroundColor: '#FFE55C',
              },
            }}
          >
            Generate Report
          </Button> */}
          <GenerateReport branchCode="BR001" />
        </StyledButton>

        <StaffRegistrationModal open={open} handleClose={handleClose} staffRole={staffRole} />
      </Box>
    </ThemeProvider>
  );
};

export default BranchManagerPage;