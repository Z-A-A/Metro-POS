import React, { useState, useEffect } from 'react';
import { Grid2, Card, CardContent, Typography, Paper } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { motion } from 'framer-motion';
import { Line, Bar, Pie } from 'react-chartjs-2';
import axios from 'axios';
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

const Dashboard = () => {
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const response = await axios.get('/api/adminDash/adminDashboard', {
          params: { id: 'some-id' }, // Replace 'some-id' with the actual ID you need to pass
        });
        setDashboardData(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching dashboard data:', error);
        setError('Failed to load dashboard data');
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  if (loading) return <Typography>Loading...</Typography>;
  if (error) return <Typography color="error">{error}</Typography>;

  const { totalSalesThisWeek, totalVendors, totalSalesOverall, productsSoldThisWeek, averageProductSaleValue, salesGraphByVendors, salesPieChartByProducts, totalUniqueCustomers } = dashboardData;

  const salesGraphData = {
    labels: Object.values(salesGraphByVendors).map(vendor => vendor.name),
    datasets: [
      {
        label: 'Sales by Vendor',
        data: Object.keys(salesGraphByVendors),
        backgroundColor: 'rgba(255, 215, 0, 0.6)',
        borderColor: 'rgba(255, 215, 0, 1)',
        borderWidth: 1,
      },
    ],
  };

  const salesPieData = {
    labels: Object.values(salesPieChartByProducts).map(product => product.name),
    datasets: [
      {
        label: 'Sales by Product',
        data: Object.keys(salesPieChartByProducts),
        backgroundColor: [
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(75, 192, 192, 0.6)',
          'rgba(153, 102, 255, 0.6)',
          'rgba(255, 159, 64, 0.6)',
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
        ],
        borderWidth: 1,
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
        <Grid2 container spacing={3} style={{ marginBottom: '20px' }}>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Total Sales This Week
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    ${totalSalesThisWeek}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Number of Vendors
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    {totalVendors}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Total Sales Overall
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    ${totalSalesOverall}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Products Sold This Week
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    {productsSoldThisWeek}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Average Product Sale Value
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    ${averageProductSaleValue}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
          <Grid2 item xs={12} sm={6} md={3}>
            <motion.div whileHover={{ scale: 1.05 }}>
              <Card
                style={{
                  backgroundColor: darkTheme.palette.background.paper,
                  boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                  borderRadius: '10px',
                  height: '110px', // Fixed height
                }}
              >
                <CardContent>
                  <Typography variant="h6" style={{ color: '#FFD700' }}>
                    Total Unique Customers
                  </Typography>
                  <Typography variant="h4" style={{ color: '#FFFFFF' }}>
                    {totalUniqueCustomers}
                  </Typography>
                </CardContent>
              </Card>
            </motion.div>
          </Grid2>
        </Grid2>

        {/* Charts Section */}
        <Grid2 container spacing={3}>
          {/* Sales Graph */}
          <Grid2 item xs={12} sm={6} md={3}>
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
          </Grid2>

          {/* Sales by Vendor */}
          <Grid2 item xs={12} sm={6} md={3}>
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
              <Bar data={salesGraphData} />
            </Paper>
          </Grid2>

          {/* Sales by Goods Type */}
          <Grid2 item xs={12} sm={6} md={3}>
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
              <Pie data={salesPieData} />
            </Paper>
          </Grid2>

          {/* Customer Growth */}
          <Grid2 item xs={12} sm={6} md={3}>
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
          </Grid2>
        </Grid2>
      </div>
    </ThemeProvider>
  );
};


export default Dashboard;