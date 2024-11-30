import React from 'react';
import { Grid, Card, CardContent, Typography, Paper } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { motion } from 'framer-motion';
import { Line, Bar, Pie } from 'react-chartjs-2';
import 'chart.js/auto';

// Theme Configuration
const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#FFD700', // Yellow accents
    },
    background: {
      default: '#001F54', // Navy blue background
      paper: '#012A63', // Slightly lighter navy for cards
    },
  },
  typography: {
    fontFamily: 'Roboto, Arial',
    fontWeightBold: 600,
  },
});

// Mock Data for Cards
const cardsData = [
  { title: 'Total Sales This Week', value: '$12,340' },
  { title: 'Number of Vendors', value: '15' },
  { title: 'Total Amount of Sales', value: '$89,765' },
  { title: 'Products Sold This Week', value: '1,245' },
  { title: 'Average Sale Value', value: '$72' },
  { title: 'New Customers', value: '120' },
  { title: 'Returning Customers', value: '80' },
  { title: 'Total Revenue', value: '$150,000' },
];

// Mock Data for Charts
const salesGraphData = {
  labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
  datasets: [
    {
      label: 'Sales ($)',
      data: [1500, 1800, 2000, 2200, 2400, 2600, 3000],
      borderColor: '#FFD700',
      backgroundColor: 'rgba(255, 215, 0, 0.2)',
    },
  ],
};

const salesByVendorData = {
  labels: ['Vendor A', 'Vendor B', 'Vendor C', 'Vendor D'],
  datasets: [
    {
      label: 'Sales by Vendor ($)',
      data: [10000, 12000, 8000, 9000],
      backgroundColor: ['#FFD700', '#FFC300', '#FF5733', '#C70039'],
    },
  ],
};

const salesByGoodsTypeData = {
  labels: ['Groceries', 'Electronics', 'Clothing', 'Others'],
  datasets: [
    {
      label: 'Sales by Goods Type',
      data: [40000, 25000, 12000, 10000],
      backgroundColor: ['#FFD700', '#FFC300', '#FF5733', '#C70039'],
    },
  ],
};

const customerGrowthData = {
  labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
  datasets: [
    {
      label: 'New Customers',
      data: [30, 50, 40, 60, 70, 80, 100],
      borderColor: '#FFD700',
      backgroundColor: 'rgba(255, 215, 0, 0.2)',
    },
    {
      label: 'Returning Customers',
      data: [20, 30, 25, 35, 45, 50, 60],
      borderColor: '#FF5733',
      backgroundColor: 'rgba(255, 87, 51, 0.2)',
    },
  ],
};

const Dashboard = () => {
  return (
    <ThemeProvider theme={darkTheme}>
      <div
        style={{
          padding: '20px',
          backgroundColor: darkTheme.palette.background.default,
          minHeight: '100vh',
        }}
      >
        <Typography
          variant="h4"
          gutterBottom
          style={{ color: darkTheme.palette.primary.main, textAlign: 'center', marginBottom: '30px' }}
        >
          Supermarket Admin Dashboard
        </Typography>

        {/* Cards Section */}
        <Grid container spacing={3} style={{ marginBottom: '20px' }}>
          {cardsData.map((card, index) => (
            <Grid item xs={12} sm={6} md={3} key={index}>
              <motion.div whileHover={{ scale: 1.05 }}>
                <Card
                  style={{
                    backgroundColor: darkTheme.palette.background.paper,
                    boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                    // border: '1px solid #FFD700',
                    borderRadius: '10px',
                    height: '110px', // Fixed height
                  }}
                >
                  <CardContent>
                    <Typography variant="h6" style={{ color: '#FFD700' }}>
                      {card.title}
                    </Typography>
                    <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                      {card.value}
                    </Typography>
                  </CardContent>
                </Card>
              </motion.div>
            </Grid>
          ))}
        </Grid>

        {/* Charts Section */}
        <Grid container spacing={3}>
          {/* Sales Graph */}
          <Grid item xs={12} sm={6} md={3}>
            <Paper
              style={{
                padding: '10px',
                backgroundColor: darkTheme.palette.background.paper,
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                borderRadius: '10px',
                height: '300px', // Reduced height
              }}
            >
              <Typography variant="h6" gutterBottom style={{ color: '#FFD700' }}>
                Weekly Sales Trend
              </Typography>
              <Line data={salesGraphData} />
            </Paper>
          </Grid>

          {/* Sales by Vendor */}
          <Grid item xs={12} sm={6} md={3}>
            <Paper
              style={{
                padding: '10px',
                backgroundColor: darkTheme.palette.background.paper,
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                borderRadius: '10px',
                height: '300px', // Reduced height
              }}
            >
              <Typography variant="h6" gutterBottom style={{ color: '#FFD700' }}>
                Sales by Vendor
              </Typography>
              <Bar data={salesByVendorData} />
            </Paper>
          </Grid>

          {/* Sales by Goods Type */}
          <Grid item xs={12} sm={6} md={3}>
            <Paper
              style={{
                padding: '10px',
                backgroundColor: darkTheme.palette.background.paper,
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                borderRadius: '10px',
                height: '300px', // Reduced height
              }}
            >
              <Typography variant="h6" gutterBottom style={{ color: '#FFD700' }}>
                Sales by Goods Type
              </Typography>
              <Pie data={salesByGoodsTypeData} />
            </Paper>
          </Grid>

          {/* Customer Growth */}
          <Grid item xs={12} sm={6} md={3}>
            <Paper
              style={{
                padding: '10px',
                backgroundColor: darkTheme.palette.background.paper,
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                borderRadius: '10px',
                height: '300px', // Reduced height
              }}
            >
              <Typography variant="h6" gutterBottom style={{ color: '#FFD700' }}>
                Customer Growth
              </Typography>
              <Line data={customerGrowthData} />
            </Paper>
          </Grid>
        </Grid>
      </div>
    </ThemeProvider>
  );
};

export default Dashboard;