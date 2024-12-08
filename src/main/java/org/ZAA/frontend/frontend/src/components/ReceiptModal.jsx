import React from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  Typography,
  Box,
  Divider,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from '@mui/material';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import metroLogo from '../assets/metro_logo.png'; // Assuming this path is correct.

const ReceiptModal = ({ open, onClose, receiptData }) => {
    const handleDownloadPDF = () => {
        const doc = new jsPDF();
      
        // Add Logo
        const img = new Image();
        img.src = metroLogo;
        doc.addImage(img, 'PNG', 80, 10, 50, 20);
      
        // Store Details
        doc.setFontSize(18);
        doc.text('METRO', 10, 40);
        doc.setFontSize(14);
        doc.text('Cash and Carry', 10, 50);
        doc.setFontSize(12);
        doc.text(`Cashier: ${receiptData.cashierName}`, 10, 60);
        doc.text(`Bill Number: ${receiptData.billNumber}`, 10, 65);
        doc.text(`Date: ${receiptData.date}`, 10, 70);
      
        // Items Table
        doc.autoTable({
          startY: 80,
          head: [['Item', 'Quantity', 'Cost', 'Total Cost']],
          body: receiptData.items.map((item) => [
            item.name,
            item.quantity,
            `$${item.cost}`,
            `$${(item.quantity * item.cost).toFixed(2)}`,
          ]),
        });
      
        // Summary Section
        const finalY = doc.lastAutoTable.finalY || 80; // Get the Y position after the table
        doc.text(`Total Cost: $${receiptData.totalBill.toFixed(2)}`, 10, finalY + 10);
        doc.text(`GST (17%): $${receiptData.gst.toFixed(2)}`, 10, finalY + 20);
        doc.text(
          `Final Total (incl. GST): $${(receiptData.totalBill + receiptData.gst).toFixed(2)}`,
          10,
          finalY + 30
        );
        doc.text('Thank you for shopping with us!', 10, finalY + 40);
      
        doc.save('receipt.pdf');
      };
      
  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle>
        <Box textAlign="center">
          <Box
            component="img"
            src={metroLogo}
            alt="METRO Logo"
            sx={{ width: 100, mb: 1 }}
          />
          <Typography variant="h5">METRO</Typography>
          <Typography variant="h6" color="textSecondary">
            Cash and Carry
          </Typography>
        </Box>
      </DialogTitle>
      <DialogContent>
        <Box textAlign="center" mb={2}>
          <Typography variant="subtitle1" color="textSecondary">
            Cashier: {receiptData.cashierName}
          </Typography>
          <Typography variant="subtitle1" color="textSecondary">
            Bill Number: {receiptData.billNumber}
          </Typography>
          <Typography variant="subtitle1" color="textSecondary">
            Date: {receiptData.date}
          </Typography>
        </Box>
        <Divider />
        <Box my={2}>
            
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Item</TableCell>
                  <TableCell>Quantity</TableCell>
                  <TableCell>Cost</TableCell>
                  <TableCell>Total Cost</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {receiptData.items.map((item, index) => (
                  <TableRow key={index}>
                    <TableCell>{item.name}</TableCell>
                    <TableCell>{item.quantity}</TableCell>
                    <TableCell>${item.cost}</TableCell>
                    <TableCell>${(item.quantity * item.cost).toFixed(2)}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Box>
        <Divider />
        <Box mt={2}>
          <Typography variant="body1">
            <strong>Total Cost:</strong> ${receiptData.totalBill.toFixed(2)}
          </Typography>
          <Typography variant="body1">
            <strong>GST (17%):</strong> ${receiptData.gst.toFixed(2)}
          </Typography>
          <Typography variant="body1" mt={1}>
            <strong>Final Total (incl. GST):</strong>{' '}
            ${(receiptData.totalBill + receiptData.gst).toFixed(2)}
          </Typography>
        </Box>
        <Box mt={2} textAlign="center">
          <Typography variant="body2" color="textSecondary">
            Thank you for shopping with us!
          </Typography>
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleDownloadPDF} variant="contained" color="primary">
          Download PDF
        </Button>
        <Button onClick={onClose} variant="outlined" color="secondary">
          Close
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ReceiptModal;
