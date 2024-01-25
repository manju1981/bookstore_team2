export const setItem = (value, key) => {
  const res = JSON.stringify(value);
  localStorage.setItem(key || "cartData", res);
};

export const getItem = (key) => {
  let res = localStorage.getItem(key || "cartData");
  res = JSON.parse(res);
  return res || [];
};

export const getItemById = (id) => {
  const cartData = getItem();
  if (cartData?.length) {
    let index = 0;
    const temp = cartData?.find((a, i) => {
      if (a?.id === id) {
        index = i;
        return true;
      }
      return false;
    });
    return { data: temp, index };
  }
  return null;
};

export const updateItem = (item, qty) => {
  // if (!qty) {
  //   const confirm = window.confirm("Are you sure want to remove this item?");
  //   if (confirm) {
  //     return removeFromCart(item);
  //   }
  //   return null;
  // }
  const cartData = getItem();
  const { data, index } = getItemById(item?.id);
  cartData[index] = { ...data, quantity: Number(qty) || "" };
  setItem(cartData);
  return cartData;
};

export const clearAllData = () => {
  localStorage.removeItem("cartData");
  localStorage.clear();
};

export const isNewInCart = (bookDetails) => {
  const cartData = getItem();
  if (cartData?.length) {
    const val = cartData.find((a) => a.id === bookDetails?.id);
    return !val;
  }
  return true;
};

export const removeFromCart = (bookDetails) => {
  const cartData = getItem();
  if (cartData?.length) {
    const val = cartData.filter((a) => a.id !== bookDetails?.id);
    setItem(val);
    return val;
  }
  return [];
};

export const getTotalCartAmountQty = () => {
  const cartData = getItem();
  let total = 0;
  let qty = 0;
  if (cartData?.length) {
    cartData.forEach(({ price, quantity }) => {
      total += price * (Number(quantity) || 0);
      qty += Number(quantity) || 0;
    });

    const additionalAmount = 0; //Some additional amounts, change here if needed
    total += additionalAmount;
  }
  return { total, qty };
};

export const getTotalCartQuantity = () => {
  const cartData = getItem();
  let total = 0;
  if (cartData?.length) {
    cartData.forEach(({ quantity }) => {
      total += Number(quantity) || 0;
      console.log("total ", total);
    });
  }
  return total;
};
