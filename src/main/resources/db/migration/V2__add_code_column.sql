-- Add code column to purchase table
ALTER TABLE purchase ADD COLUMN code VARCHAR(50) NOT NULL DEFAULT '';

-- Create an index for faster lookups by code
CREATE INDEX idx_purchase_code ON purchase (code);