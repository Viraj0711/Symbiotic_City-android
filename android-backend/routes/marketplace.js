const express = require('express');
const router = express.Router();
const pool = require('../db');
const { authenticateToken } = require('../middleware/auth');

// Get all marketplace items (public)
router.get('/', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        m.*,
        u.name as seller_name
      FROM marketplace m
      LEFT JOIN users u ON m.seller_id = u.id
      ORDER BY m.created_at DESC
    `);

    res.json(result.rows);
  } catch (error) {
    console.error('Get marketplace items error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Get marketplace item by ID (public)
router.get('/:id', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        m.*,
        u.name as seller_name
      FROM marketplace m
      LEFT JOIN users u ON m.seller_id = u.id
      WHERE m.id = $1
    `, [req.params.id]);

    if (result.rows.length === 0) {
      return res.status(404).json({ message: 'Item not found' });
    }

    res.json(result.rows[0]);
  } catch (error) {
    console.error('Get marketplace item error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Create marketplace item
router.post('/', authenticateToken, async (req, res) => {
  try {
    const { title, description, price, category } = req.body;
    const userId = req.user.id;

    const result = await pool.query(`
      INSERT INTO marketplace (title, description, price, category, seller_id)
      VALUES ($1, $2, $3, $4, $5)
      RETURNING *
    `, [title, description, price, category, userId]);

    res.status(201).json(result.rows[0]);
  } catch (error) {
    console.error('Create marketplace item error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

module.exports = router;
