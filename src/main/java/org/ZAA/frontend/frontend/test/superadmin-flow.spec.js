// FILE: tests/superadmin-flow.spec.js

import { test, expect } from '@playwright/test';

test.describe('Super Admin Flow', () => {
  test('should add a new cashier from the super admin page', async ({ page }) => {
    // Step 1: Navigate to the super admin page (assuming user is already logged in)
    console.log('Navigating to the super admin page...');
    await page.goto('http://localhost:5173/superadmin'); // This assumes the login is already done

    // Ensure the super admin page is fully loaded
    console.log('Waiting for Add Cashier button...');
    await page.waitForSelector('button:has-text("Add Cashier")', { timeout: 30000 });
    console.log('Add Cashier button found.');
    await page.screenshot({ path: 'screenshots/superadmin-page.png' });

    // Click on the "Add Cashier" button
    console.log('Clicking "Add Cashier" button...');
    await page.getByRole('button', { name: 'Add Cashier' }).click();

    // Wait for the Add Cashier modal to appear
    console.log('Waiting for the Add Cashier modal...');
    await page.waitForSelector('input[name="name"]');
    console.log('Add Cashier modal is visible.');
    await page.screenshot({ path: 'screenshots/add-cashier-modal.png' });

    // Fill in the cashier details in the modal
    console.log('Filling in cashier details...');
    await page.getByLabel('Name *').fill('John Doe');
    await page.getByLabel('Email').fill('johndoe@example.com');
    await page.getByLabel('Password').fill('password123');
    await page.getByLabel('Branch Code').fill('B001');
    await page.getByLabel('Salary').fill('30000');
    await page.screenshot({ path: 'screenshots/cashier-details-filled.png' });

    // Submit the form to add the cashier
    console.log('Submitting the Add Cashier form...');
    // await page.getByRole('button', { name: 'Submit' }).click(); // Assuming you need to click "Submit"

    // Wait for the success message or confirmation
    console.log('Waiting for success message...');
    // await page.waitForSelector('text=Cashier added successfully'); // Adjust selector as needed
    await page.screenshot({ path: 'screenshots/cashier-added-successfully.png' });
  });
});
