const dotenv = require('dotenv');
const { Pool } = require('pg');

dotenv.config();

const pool = new Pool({
  connectionString: process.env.DATABASE_URL,
  ssl: {
    rejectUnauthorized: false
  }
});

async function testConnection() {
  try {
    console.log('Testing database connection...');
    const result = await pool.query('SELECT NOW()');
    console.log('✅ Database connected successfully!');
    console.log('Current time:', result.rows[0].now);
    
    // Test projects table
    const projects = await pool.query('SELECT COUNT(*) FROM projects');
    console.log(`Projects count: ${projects.rows[0].count}`);
    
    // Test events table
    const events = await pool.query('SELECT COUNT(*) FROM events');
    console.log(`Events count: ${events.rows[0].count}`);
    
    // Test marketplace table
    const marketplace = await pool.query('SELECT COUNT(*) FROM marketplace');
    console.log(`Marketplace items count: ${marketplace.rows[0].count}`);
    
    await pool.end();
  } catch (error) {
    console.error('❌ Database error:', error.message);
    console.error('Full error:', error);
    process.exit(1);
  }
}

testConnection();
