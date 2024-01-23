import { render, screen } from "@testing-library/react";
import Books from "./Books";
const books = [
  {
    "id": 1,
    "title": "Atomic Habits",
    "bookId": "BOOKS-10001",
    "author": "James Clear",
    "description": "Some Description",
    "rating": 5,
    "img": "https://prh.imgix.net/articles/atomichabits-1600x800-05.jpg",
    "price": 699.0,
    "quantity": 0
  },
  {
    "id": 6,
    "title": "Code of the Extraordinary",
    "bookId": "BOOKS-10006",
    "author": "Vishen Lakhiani",
    "description": "Some Description",
    "rating": 5,
    "img": "https://pbs.twimg.com/media/ENPVm8qU0AQbiXk.jpg:large",
    "price": 3899.0,
    "quantity": 0
  },
  {
    "id": 2,
    "title": "IKIGAI",
    "bookId": "BOOKS-10002",
    "author": "Japanese Author",
    "description": "Some Description",
    "rating": 5,
    "img": "https://m.media-amazon.com/images/S/aplus-media-library-service-media/92a8e744-a6ea-4414-814e-72faac304256.__CR0,0,1200,1200_PT0_SX300_V1___.jpg",
    "price": 299.0,
    "quantity": 0
  },
  {
    "id": 5,
    "title": "Psychology of Money",
    "bookId": "BOOKS-10005",
    "author": "Brian Herbert",
    "description": "Some Description",
    "rating": 5,
    "img": "https://miro.medium.com/v2/resize:fit:1400/1*rQNN_T1ueoC3lJVxcNZEjg.jpeg",
    "price": 899.0,
    "quantity": 0
  },
  {
    "id": 3,
    "title": "Think Like a Monk",
    "bookId": "BOOKS-10003",
    "author": "Brian Herbert",
    "description": "Some Description",
    "rating": 5,
    "img": "https://media.licdn.com/dms/image/D4D12AQGcBZHekpNLzA/article-cover_image-shrink_600_2000/0/1683362420911?e=2147483647&v=beta&t=9Qj_QdYBPvqzlVzp5ltXXIOdxF4SL8nKjuRHyTlwzgA",
    "price": 899.0,
    "quantity": 0
  },
  {
    "id": 4,
    "title": "Think Like a Monk",
    "bookId": "BOOKS-10004",
    "author": "Brian Herbert",
    "description": "Some Description",
    "rating": 5,
    "img": "https://media.licdn.com/dms/image/D4D12AQGcBZHekpNLzA/article-cover_image-shrink_600_2000/0/1683362420911?e=2147483647&v=beta&t=9Qj_QdYBPvqzlVzp5ltXXIOdxF4SL8nKjuRHyTlwzgA",
    "price": 899.0,
    "quantity": 0
  }
]
describe("Books Container", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });
  it("should render the list of books", () => {
    render(<Books books={books}/>);
    expect(screen.getByTestId("Books-test")).toBeInTheDocument();
  });
});
