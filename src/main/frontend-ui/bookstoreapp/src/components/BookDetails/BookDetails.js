import React from "react";
import {
    Image,
    HeaderSubheader,
    Header,
    Dropdown,
    ButtonContent,
    Button,
    Icon
} from "semantic-ui-react";
import { useParams } from "react-router-dom";
import { Container, DetailsContatiner, ImageContainer, ButtonContainer } from './BookDetails.style';

const bookDetail = {
    img: "https://prh.imgix.net/articles/atomichabits-1600x800-05.jpg",
    title: "Atomic Habits",
    author: "James Clear",
    desc: "Atomic Habits by James Clear is a comprehensive, practical guide on how to change your habits and get 1% better every day. Using a framework called the Four Laws of Behavior Change, Atomic Habits teaches readers a simple set of rules for creating good habits and breaking bad ones. Read the full summary to glean 3 key lessons from Atomic Habits, learn how to build a habit in 4 simple steps, and get a handy reference guide for the strategies recommended throughout the book."
};
const countryOptions = [
    { key: 'in', value: 'in', flag: 'in', text: 'India' },
    { key: 'af', value: 'af', flag: 'af', text: 'Afghanistan' },
    { key: 'ax', value: 'ax', flag: 'ax', text: 'Aland Islands' },
    { key: 'al', value: 'al', flag: 'al', text: 'Albania' },
    { key: 'dz', value: 'dz', flag: 'dz', text: 'Algeria' },
    { key: 'as', value: 'as', flag: 'as', text: 'American Samoa' },
    { key: 'ad', value: 'ad', flag: 'ad', text: 'Andorra' },
    { key: 'ao', value: 'ao', flag: 'ao', text: 'Angola' },
    { key: 'ai', value: 'ai', flag: 'ai', text: 'Anguilla' },
    { key: 'ag', value: 'ag', flag: 'ag', text: 'Antigua' },
    { key: 'ar', value: 'ar', flag: 'ar', text: 'Argentina' },
    { key: 'am', value: 'am', flag: 'am', text: 'Armenia' },
    { key: 'aw', value: 'aw', flag: 'aw', text: 'Aruba' },
    { key: 'au', value: 'au', flag: 'au', text: 'Australia' },
    { key: 'at', value: 'at', flag: 'at', text: 'Austria' },
    { key: 'az', value: 'az', flag: 'az', text: 'Azerbaijan' },
    { key: 'bs', value: 'bs', flag: 'bs', text: 'Bahamas' },
    { key: 'bh', value: 'bh', flag: 'bh', text: 'Bahrain' },
    { key: 'bd', value: 'bd', flag: 'bd', text: 'Bangladesh' },
    { key: 'bb', value: 'bb', flag: 'bb', text: 'Barbados' },
    { key: 'by', value: 'by', flag: 'by', text: 'Belarus' },
    { key: 'be', value: 'be', flag: 'be', text: 'Belgium' },
    { key: 'bz', value: 'bz', flag: 'bz', text: 'Belize' },
    { key: 'bj', value: 'bj', flag: 'bj', text: 'Benin' },
  ]
const BookDetails = () => {
    const { id } = useParams();
    return (
        <div style={Container}>
            <Image style={ImageContainer} src={bookDetail.img} size="large" floated="left" />
            <div style={DetailsContatiner}>
                <Header style={{ fontSize: '40px'}} textAlign="left" as="h1">
                    {bookDetail.title}
                    <Header style={{fontSize: '20px', marginTop: '0.5rem'}} as="h4">
                        {bookDetail.author}
                        <HeaderSubheader style={{fontSize: '18px', marginTop: '2rem',  numberOfLines: 5, ellipsizeMode:'head'}} as="h4">
                            {bookDetail.desc}
                        </HeaderSubheader>
                    </Header>
                </Header>

                <Dropdown
                    placeholder="Select your country"
                    options={countryOptions}
                    search
                    selection
                    data-testid="country-dropdown"
                    style={{ marginTop: '1rem'}}
                />
                <Button circular={true} size='large' style={ButtonContainer} animated='horizontal'>
                    <ButtonContent hidden>BUY</ButtonContent>
                    <ButtonContent visible>
                        <Icon name='shop' />
                    </ButtonContent>
                </Button>            
            </div>
        </div>
    );
};

export default BookDetails;