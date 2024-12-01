import React from 'react';
import { Grid, Paper, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import Chart from '../components/Chart';

const salesTableData = [
  { date: '2024-11-28', product: 'Apples', quantity: 50, total: '$150' },
  { date: '2024-11-28', product: 'Bananas', quantity: 30, total: '$90' },
  { date: '2024-11-28', product: 'Carrots', quantity: 20, total: '$40' },
  { date: '2024-11-28', product: 'Milk', quantity: 10, total: '$25' },
];

const Sales = () => (
  <div style={{ padding: '20px' }}>
    <Typography variant="h4" gutterBottom>
      Sales Analytics
    </Typography>
    <Grid container spacing={3}>
      <Grid item xs={12} md={8}>
        <Paper style={{ padding: '20px' }}>
          <Typography variant="h6" gutterBottom>
            Weekly Sales Trend
          </Typography>
          <Chart />
        </Paper>
      </Grid>
      <Grid item xs={12} md={4}>
        <Paper style={{ padding: '20px' }}>
          <Typography variant="h6" gutterBottom>
            Recent Transactions
          </Typography>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Date</TableCell>
                  <TableCell>Product</TableCell>
                  <TableCell>Quantity</TableCell>
                  <TableCell>Total</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {salesTableData.map((row, index) => (
                  <TableRow key={index}>
                    <TableCell>{row.date}</TableCell>
                    <TableCell>{row.product}</TableCell>
                    <TableCell>{row.quantity}</TableCell>
                    <TableCell>{row.total}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Paper>
      </Grid>
    </Grid>
  </div>
);

export default Sales;
