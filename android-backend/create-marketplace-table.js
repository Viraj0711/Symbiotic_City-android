const dotenv = require('dotenv');
const { Pool } = require('pg');

dotenv.config();

const pool = new Pool({
  connectionString: process.env.DATABASE_URL,
  ssl: {
    rejectUnauthorized: false
  }
});

async function createMarketplaceTable() {
  try {
    console.log('Creating marketplace table...');
    
    await pool.query(`
      CREATE TABLE IF NOT EXISTS marketplace (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        price DECIMAL(10, 2),
        category VARCHAR(100),
        image_url TEXT,
        status VARCHAR(50) DEFAULT 'available',
        seller_id UUID,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      )
    `);
    
    console.log('✅ Marketplace table created successfully!');
    
    // Insert sample data (without seller_id for now)
    console.log('Inserting sample marketplace items...');
    
    await pool.query(`
      INSERT INTO marketplace (title, description, price, category, status)
      VALUES 
        ('Solar Panel Kit', 'Complete 5kW solar panel installation kit for homes', 2500.00, 'Energy', 'available'),
        ('Compost Bin', 'Large capacity composting bin for organic waste', 89.99, 'Gardening', 'available'),
        ('Rainwater Harvesting System', '500L rainwater collection and filtration system', 450.00, 'Water', 'available'),
        ('LED Grow Lights', 'Energy-efficient grow lights for indoor gardening', 125.00, 'Gardening', 'available'),
        ('Electric Bicycle', 'Eco-friendly electric bike with 50km range', 1200.00, 'Transportation', 'available')
      ON CONFLICT DO NOTHING
    `);
    
    console.log('✅ Sample data inserted!');
    
    const result = await pool.query('SELECT COUNT(*) FROM marketplace');
    console.log(`Total marketplace items: ${result.rows[0].count}`);
    
    await pool.end();
  } catch (error) {
    console.error('❌ Error:', error.message);
    console.error('Full error:', error);
    process.exit(1);
  }
}

createMarketplaceTable();
