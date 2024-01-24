import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Card from './index';

const mockData = {
  img: 'mock-image-url',
  title: 'Mock Title',
  rating: 3,
  author: 'Mock Author',
  price: 20.0,
};

describe('Card Component', () => {
  it('renders card with correct data', () => {
    render(<Card data={mockData} />);

    // Check if the card contains the expected elements and data
    expect(screen.getByAltText(mockData.img)).toBeInTheDocument();
    expect(screen.getByText(mockData.title)).toBeInTheDocument();
    expect(screen.getByText(mockData.author)).toBeInTheDocument();
    expect(screen.getByText(`Rs. ${mockData.price}`)).toBeInTheDocument();
  });
});
