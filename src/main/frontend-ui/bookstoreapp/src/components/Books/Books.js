import React from "react";
import Search from "../Search/Search";
import Card from "../Cards";

const Books = () => {
  const data = [
    {
      img: "https://prh.imgix.net/articles/atomichabits-1600x800-05.jpg",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
    {
      img: "https://m.media-amazon.com/images/S/aplus-media-library-service-media/92a8e744-a6ea-4414-814e-72faac304256.__CR0,0,1200,1200_PT0_SX300_V1___.jpg",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
    {
      img: "https://is1-ssl.mzstatic.com/image/thumb/Publication114/v4/e4/7e/98/e47e9839-4868-a85e-4544-12921d33d767/9781647001827.jpg/1200x630wz.png",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
    {
      img: "https://media.licdn.com/dms/image/D4D12AQGcBZHekpNLzA/article-cover_image-shrink_600_2000/0/1683362420911?e=2147483647&v=beta&t=9Qj_QdYBPvqzlVzp5ltXXIOdxF4SL8nKjuRHyTlwzgA",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
    {
      img: "https://miro.medium.com/v2/resize:fit:1400/1*rQNN_T1ueoC3lJVxcNZEjg.jpeg",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
    {
      img: "https://pbs.twimg.com/media/ENPVm8qU0AQbiXk.jpg:large",
      title: "Book",
      author: "Author",
      desc: "Description",
    },
  ];
  return (
    <div className="body-container">
      <Search />
      <div className="cord-collection-wrapper">
        {data.map((item, index) => (
          <Card key={index} data={item} />
        ))}
      </div>
    </div>
  );
};

export default Books;
