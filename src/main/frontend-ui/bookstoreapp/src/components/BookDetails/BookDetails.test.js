import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter, Routes, Route } from 'react-router-dom';
import BookDetails from './BookDetails';

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useParams: jest.fn(),
}));

describe('BookDetails Component', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it.only('renders book details correctly', async () => {
    // Mock the fetch function to return mock data
    global.fetch = jest.fn().mockResolvedValueOnce({
      json: jest.fn().mockResolvedValueOnce({
        img: 'mock-image-url',
        title: 'Atomic Habits',
        author: 'James Clear',
        description: 'Mock description',
        price: 20.0,
      }),
    }).mockResolvedValueOnce({
                 json: jest.fn().mockResolvedValueOnce([{
                 id:1,
                 name:'IND'
                 },
                 {
                 id:2,
                 name:'US'
                 }]),
               });;


    // Mock useParams to return a mock ID
    jest.spyOn(require('react-router-dom'), 'useParams').mockReturnValue({ id: '1' });

    render(
      <MemoryRouter initialEntries={['/book/1']}>
        <Routes>
          <Route path="/book/:id" element={<BookDetails />} />
        </Routes>
      </MemoryRouter>
    );

    // Wait for the asynchronous operation to complete
    await waitFor(() => {
      expect(screen.getByText('Atomic Habits')).toBeInTheDocument();
      expect(screen.getByText('James Clear')).toBeInTheDocument();
      expect(
        screen.getByText(
          'Mock description'
        )
      ).toBeInTheDocument();
      expect(screen.getByTestId('country-dropdown')).toBeInTheDocument();
      expect(screen.getByText('BUY')).toBeInTheDocument();
    });
  });

  it('handles user interaction', async () => {
    // Mock the fetch function to return mock data
    global.fetch = jest.fn().mockResolvedValue({
          json: jest.fn().mockResolvedValue({
            img: 'mock-image-url',
            title: 'Atomic Habits',
            author: 'James Clear',
            description: 'Mock description',
            price: 20.0,
          }),
        });

    // Mock useParams to return a mock ID
    jest.spyOn(require('react-router-dom'), 'useParams').mockReturnValue({ id: '1' });

    render(
      <MemoryRouter initialEntries={['/book/1']}>
        <Routes>
          <Route path="/book/:id" element={<BookDetails />} />
        </Routes>
      </MemoryRouter>
    );

    // Wait for the asynchronous operation to complete
    await waitFor(() => {
      userEvent.click(screen.getByText('BUY'));
      // Add your user interaction assertions here
    });
  });
});
