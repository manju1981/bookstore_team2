describe("Show Details", () => {
  it("should render the heading component", () => {
    const { container } = render(<ShowDetails {...headingProps} />);
    expect(container).toMatchSnapshot();
  });
});
