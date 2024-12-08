import React, { useState } from 'react';
import axios from 'axios';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { Button, Typography, Box } from '@mui/material';
import DescriptionIcon from '@mui/icons-material/Description';
import { Bar } from 'react-chartjs-2';

const GenerateReport = ({ branchCode = 'BR001' }) => {
  const [reportData, setReportData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const generateReport = async () => {
    setLoading(true);
    setError(null);
    try {
      const endpoints = [
        { type: 'Today', url: '/report/today' },
        { type: 'Weekly', url: '/report/weekly' },
        { type: 'Monthly', url: '/report/monthly' },
        { type: 'Yearly', url: '/report/yearly' },
      ];

      const responses = await Promise.all(
        endpoints.map(endpoint =>
          axios.get(endpoint.url, { params: { branchCode } }).then(response => ({
            type: endpoint.type,
            data: response.data,
          }))
        )
      );

      setReportData(responses);
      createPDF(responses);
    } catch (err) {
      console.error('Error generating report:', err);
      setError('Failed to generate report.');
    } finally {
      setLoading(false);
    }
  };

  const createPDF = (data) => {
    const doc = new jsPDF();
    doc.setFontSize(16);
    doc.text('Branch Report', 10, 10);
    doc.setFontSize(12);

    data.forEach((report, index) => {
      const yOffset = 20 + index * 50;
      const branchCode = report.data.branchCode || 'N/A';
      const sales = report.data.sales !== undefined ? `$${report.data.sales}` : 'N/A';
      const remainingStock = report.data.remainingStock !== undefined ? report.data.remainingStock : 'N/A';
      const profit = report.data.profit !== undefined ? `$${report.data.profit}` : 'N/A';
    
      doc.text(`${report.type} Report`, 10, yOffset);
      doc.text(`Branch Code: ${branchCode}`, 10, yOffset + 10);
      doc.text(`Sales: ${sales}`, 10, yOffset + 20);
      doc.text(`Remaining Stock: ${remainingStock}`, 10, yOffset + 30);
      doc.text(`Profit: ${profit}`, 10, yOffset + 40);
    });
    

    // Example Table
    doc.autoTable({
      startY: 120,
      head: [['Type', 'Sales', 'Remaining Stock', 'Profit']],
      body: data.map(report => [
        report.type,
        `$${report.data.sales}`,
        report.data.remainingStock,
        `$${report.data.profit}`,
      ]),
    });

    doc.save('branch_report.pdf');
  };

  const chartData = reportData
    ? {
        labels: reportData.map(report => report.type),
        datasets: [
          {
            label: 'Sales ($)',
            data: reportData.map(report => report.data.sales),
            backgroundColor: 'rgba(75,192,192,0.6)',
            borderColor: 'rgba(75,192,192,1)',
            borderWidth: 1,
          },
          {
            label: 'Profit ($)',
            data: reportData.map(report => report.data.profit),
            backgroundColor: 'rgba(153,102,255,0.6)',
            borderColor: 'rgba(153,102,255,1)',
            borderWidth: 1,
          },
        ],
      }
    : null;

    return (
      <Button
        variant="contained"
        startIcon={<DescriptionIcon />}
        onClick={generateReport}
        sx={{
          backgroundColor: '#FFD700',
          color: '#001F54',
          width:'300px',
          padding: '15px 30px',
          '&:hover': {
            backgroundColor: '#FFE55C',
          },
        }}
      >
        Generate Report
      </Button>
    );
};

export default GenerateReport;
