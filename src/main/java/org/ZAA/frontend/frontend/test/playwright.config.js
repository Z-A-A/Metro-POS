// FILE: playwright.config.js

import { defineConfig } from '@playwright/test';

export default defineConfig({
  reporter: [
    ['list'],
    ['html', { outputFolder: 'test-results', open: 'always' }]
  ],
  use: {
    headless: false,
    viewport: { width: 1280, height: 720 },
    ignoreHTTPSErrors: true,
    video: 'on', // Record video for failed test runs
    screenshot: 'only-on-failure',
    trace: 'retain-on-failure', // Record trace for failed test runs
  },
  outputDir: 'test-results', // Save test results in the test-results directory
});