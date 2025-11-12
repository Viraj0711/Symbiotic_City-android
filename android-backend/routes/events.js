const express = require('express');
const router = express.Router();
const pool = require('../db');
const { authenticateToken } = require('../middleware/auth');

// Get all events (public)
router.get('/', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        e.*,
        u.name as organizer_name
      FROM events e
      LEFT JOIN users u ON e.organizer_id = u.id
      ORDER BY e.start_date DESC
    `);

    res.json(result.rows);
  } catch (error) {
    console.error('Get events error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Get event by ID (public)
router.get('/:id', async (req, res) => {
  try {
    const result = await pool.query(`
      SELECT 
        e.*,
        u.name as organizer_name
      FROM events e
      LEFT JOIN users u ON e.organizer_id = u.id
      WHERE e.id = $1
    `, [req.params.id]);

    if (result.rows.length === 0) {
      return res.status(404).json({ message: 'Event not found' });
    }

    res.json(result.rows[0]);
  } catch (error) {
    console.error('Get event error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

// Create event
router.post('/', authenticateToken, async (req, res) => {
  try {
    const { title, description, location, start_date, end_date } = req.body;
    const userId = req.user.id;

    const result = await pool.query(`
      INSERT INTO events (title, description, location, start_date, end_date, organizer_id)
      VALUES ($1, $2, $3, $4, $5, $6)
      RETURNING *
    `, [title, description, location, start_date, end_date, userId]);

    res.status(201).json(result.rows[0]);
  } catch (error) {
    console.error('Create event error:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

module.exports = router;
