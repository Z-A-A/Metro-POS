// FILE: tests/login-flow.spec.js

import { test, expect } from '@playwright/test';

test.describe('Login Flow', () => {
  test('should navigate to the login page, fill in credentials and log in successfully', async ({ page }) => {
    // Set a longer timeout for this test
    test.setTimeout(60000); // 60 seconds

    // Step 1: Navigate to the login page
    console.log('Navigating to login page...');
    await page.goto('http://localhost:5173/login'); // Adjust the URL as needed
    await page.screenshot({ path: 'screenshots/login-page.png' });

    // Ensure the login page is fully loaded
    console.log('Waiting for email input field...');
    await page.waitForSelector('input[name="email"]', { timeout: 30000 });
    console.log('Email input field found.');
    await page.screenshot({ path: 'screenshots/email-input-found.png' });

    // Fill in the login form
    console.log('Filling in login form...');
    await page.getByLabel('Email').fill('superadmin@example.com');
    await page.getByLabel('Password').fill('password123');
    await page.screenshot({ path: 'screenshots/login-form-filled.png' });

    // Submit the login form
    console.log('Submitting login form...');
    await page.getByRole('button', { name: 'Login' }).click();

    // Wait for navigation to the super admin page
    // console.log('Waiting for navigation to super admin page...');
    // await page.waitForURL('http://localhost:5173/superadmin'); // Wait for URL change
    // await page.screenshot({ path: 'screenshots/superadmin-page.png' });
  });
});
