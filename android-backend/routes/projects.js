const express = require('express');
const router = express.Router();
const pool = require('../db');
const { authenticateToken } = require('../middleware/auth');

// Get all projects (public)
router.get('/', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        p.*,
        u.name as author_name,
        u.email as author_email
      FROM projects p
      LEFT JOIN users u ON p.author_id = u.id
      ORDER BY p.created_at DESC
    `);

    res.json(result.rows);
  } catch (error) {
    console.error('Get projects error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Get project by ID (public)
router.get('/:id', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        p.*,
        u.name as author_name,
        u.email as author_email
      FROM projects p
      LEFT JOIN users u ON p.author_id = u.id
      WHERE p.id = $1
    `, [req.params.id]);

    if (result.rows.length === 0) {
      return res.status(404).json({ message: 'Project not found' });
    }

    res.json(result.rows[0]);
  } catch (error) {
    console.error('Get project error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Create project
router.post('/', authenticateToken, async (req, res) => {
  try {
    const { title, description, category, tags, location } = req.body;
    const userId = req.user.id;

    const result = await pool.query(`
      INSERT INTO projects (title, description, category, tags, location, author_id)
      VALUES ($1, $2, $3, $4, $5, $6)
      RETURNING *
    `, [title, description, category, tags || [], location || '', userId]);

    res.status(201).json(result.rows[0]);
  } catch (error) {
    console.error('Create project error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

module.exports = router;
